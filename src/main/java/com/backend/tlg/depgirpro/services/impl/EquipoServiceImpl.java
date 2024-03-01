package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.EncuentrosPorEquipoDTO;
import com.backend.tlg.depgirpro.dto.RegistroEquipoDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import com.backend.tlg.depgirpro.repository.EquipoRepository;
import com.backend.tlg.depgirpro.services.EquipoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRep;
    @Override
    public ResponseEntity<?> crearEquipo(RegistroEquipoDTO dto) {
        Map<String, Object> respuesta=new HashMap<>();
        Equipo equipoNew=this.equipoRep.save(new Equipo(dto.getNombre(),dto.getCantidad_jugadores()));
        respuesta.put("equipo", equipoNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<?> listarEncuentrosDeLocal(Long idEquipo) {
        Map<String, Object> respuesta=new HashMap<>();
        List<EncuentrosPorEquipoDTO> encuentros=this.equipoRep.findEncuentrosDeLocal(idEquipo);
        if (encuentros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        respuesta.put("encuentros", encuentros);
        return ResponseEntity.ok(respuesta);
    }

    @Override
    public ResponseEntity<?> listarEncuentrosDeVisitante(Long idEquipo) {
        Map<String, Object> respuesta=new HashMap<>();
        List<EncuentrosPorEquipoDTO> encuentros=this.equipoRep.findEncuentrosDeVisitante(idEquipo);
        if (encuentros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        respuesta.put("encuentros", encuentros);
        return ResponseEntity.ok(respuesta);
    }
}
