package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.RegistroSancionDTO;
import com.backend.tlg.depgirpro.dto.SancionResponseDTO;
import com.backend.tlg.depgirpro.entity.Multa;
import com.backend.tlg.depgirpro.entity.Sancion;
import com.backend.tlg.depgirpro.repository.SancionRepository;
import com.backend.tlg.depgirpro.services.SancionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class SancionServiceImpl implements SancionService {

    private final SancionRepository sancionRep;


    @Override
    public ResponseEntity<?> insertar(RegistroSancionDTO dto) {
        Map<String, Object> respuesta=new HashMap<>();
        Multa multaNew=new Multa(dto.getMultaDTO().getMulta());
        Sancion sancionNew=this.sancionRep.save(new Sancion(dto.getSancion(), multaNew));
        SancionResponseDTO dtoResp=new SancionResponseDTO(sancionNew.getId(), sancionNew.getSancion(), sancionNew.getMulta().getMulta());
        respuesta.put("sancion", dtoResp);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
