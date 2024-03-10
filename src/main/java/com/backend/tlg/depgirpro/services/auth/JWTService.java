package com.backend.tlg.depgirpro.services.auth;

import com.backend.tlg.depgirpro.entity.CryptoKey;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.entity.SecretKeyEnc;
import com.backend.tlg.depgirpro.exceptions.InternalServerExceptionManaged;
import com.backend.tlg.depgirpro.repository.CryptoKeyRepository;
import com.backend.tlg.depgirpro.repository.SecretKeyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;
    @Autowired
    private CryptoKeyGenerator skg;
    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private CryptoKeyRepository cryptoRep;
    @Autowired
    private SecretKeyRepository secretKeyRep;
    private String secretKey;


    @PostConstruct
    public void metodoPostInyeccionDeDependencias() {
        if (this.cryptoRep.findAll().isEmpty() && this.secretKeyRep.findAll().isEmpty()){
            return;
        }else{
            //esta crypto key es la que se usará para encriptar y desencriptar la secret_key
            //se genera aleatoriamente cada vez que se sube el servidor gracias a la clase
            //CryptoKeyGenerator
            String cryptoKey=skg.generateBase64CryptoKey();

            System.out.println("cryptokey:" + cryptoKey);

            //si ya hay claves para el algoritmo bouncyCastle en la tabla crypto_keys, entonces
            //se busca el ultimo registro de la tabla crypto_keys y token_secret_key ya que
            //quiere decir que el ultimo registro de la tabla crypto_keys es la llave maestra que
            //desencripta el ultimo registro de la tabla token_secret_key, que es el secret_key que firma los tokens
            CryptoKey cryptoEntity=this.cryptoRep.encontrarUltimo();
            SecretKeyEnc secretEntity=this.secretKeyRep.encontrarUltimo();
            if (cryptoEntity.isValid() && secretEntity.isValid()) { //si ambos registros son validos entonces
                System.out.println("secret Key encriptada" + secretEntity.getSecretKey());
                System.out.println("crypto key encriptada" + cryptoEntity.getCryptoKey());
                //se desencripta la ultima secret_key de la tabla token_secret_key usando la ultima crypto_key valida
                secretKey=decrypt(secretEntity.getSecretKey(), cryptoEntity.getCryptoKey());
                System.out.println("secret key desencriptada:" + secretKey);
                //una vez desencriptada la secret_key para su uso en las peticiones,
                //se procede a invalidar esos ultimos registros validos
                cryptoEntity.setValid(false);
                secretEntity.setValid(false);
                cryptoRep.save(cryptoEntity);
                secretKeyRep.save(secretEntity);
            }else {
                throw new InternalServerExceptionManaged("No se pudo realizar el proceso de encriptado y desencriptado de la secretKey");
            }
            //se crea nuevo registro para la tabla crypto_keys con la crypto_key que se genera aleatoriamente cada vez que se ejecuta el server
            CryptoKey cryptoKeyNew=this.cryptoRep.save(new CryptoKey(cryptoKey, true));
            //se genera un nuevo registro para la tabla token_secret_key valido, utilizando la nueva crypto_key registrada
            //para encriptar nuevamente la secret_key que firma los tokens utilizando los metodos de la clase
            //CryptoService
            this.secretKeyRep.save(new SecretKeyEnc(encrypt(secretKey, cryptoKeyNew.getCryptoKey()), true));

        }


    }



    public String generarToken(Persona persona, Map<String, Object> claims){
        Date issuedAt=new Date(System.currentTimeMillis());
        Date expiration=new Date((EXPIRATION_IN_MINUTES*60*1000)+issuedAt.getTime());
        return Jwts.builder()
                .header().type("JWT")
                .and()
                .subject(persona.getCorreo())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(claims)
                .signWith(generarKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String jwt){
        return extractAllClaims(jwt).getSubject();

    }

    private Claims extractAllClaims(String jwt){
        return Jwts.parser().verifyWith(generarKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }

    private SecretKey generarKey(){
        byte[] secretAsBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    private String encrypt(String textoAEncriptar, String key) {
        try {
            return cryptoService.encrypt(textoAEncriptar, key);
        }catch(Exception e) {
            throw new InternalServerExceptionManaged("No se pudo encriptar la clave secreta");
        }
    }


    private String decrypt(String textoADesencriptar, String key) {
        try {
            return cryptoService.decrypt(textoADesencriptar, key);
        }catch(Exception e) {
            throw new InternalServerExceptionManaged("No se pudo desencriptar la clave secreta");
        }
    }
}
