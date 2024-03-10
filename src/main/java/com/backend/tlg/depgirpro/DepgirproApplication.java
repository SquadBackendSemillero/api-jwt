package com.backend.tlg.depgirpro;

import com.backend.tlg.depgirpro.entity.CryptoKey;
import com.backend.tlg.depgirpro.entity.SecretKeyEnc;
import com.backend.tlg.depgirpro.repository.CryptoKeyRepository;
import com.backend.tlg.depgirpro.repository.SecretKeyRepository;
import com.backend.tlg.depgirpro.services.auth.CryptoKeyGenerator;
import com.backend.tlg.depgirpro.services.auth.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DepgirproApplication{

	@Autowired
	private PasswordEncoder passwordEncoder;



	public static void main(String[] args) {
		SpringApplication.run(DepgirproApplication.class, args);
	}



}
