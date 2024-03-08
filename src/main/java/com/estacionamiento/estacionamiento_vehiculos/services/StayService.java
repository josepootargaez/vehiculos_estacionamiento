package com.estacionamiento.estacionamiento_vehiculos.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento_vehiculos.dto.StayDTO;
import com.estacionamiento.estacionamiento_vehiculos.dto.StayOutDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Autos;
import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;
import com.estacionamiento.estacionamiento_vehiculos.repositories.CarRepository;
import com.estacionamiento.estacionamiento_vehiculos.repositories.StayRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StayService {
    
    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Estancia> getAllStay(){
        return stayRepository.findAll();
    }

    public ResponseEntity<?> insertStay(StayDTO stayDTO) {
        try {
            Autos auto = carRepository.findByplaca(stayDTO.getPlaca());
            if (auto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("{\"error\": \"La placa proporcionada no existe\",\"success\": false}");
            }
            Estancia estancia = new Estancia();
            estancia.setPlaca(stayDTO.getPlaca());
            estancia.setFecha_entrada(LocalDateTime.now());
            estancia.setAutos(auto);
            stayRepository.save(estancia);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"success\": true}");
        } catch (Exception  e) {
            System.err.println("Error al insertar el auto: " + e.getMessage());
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }

    public ResponseEntity<?> insertOut(StayOutDTO stayDTO) {
        try {
            Long idList = stayDTO.getEstancia_id() != null ? stayDTO.getEstancia_id() : 0; 
            Estancia estancia = stayRepository.findById(idList)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró la estancia solicitada"));
            estancia.setFecha_salida(LocalDateTime.now());
            stayRepository.save(estancia);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"success\": true}");
        } catch (Exception  e) {
            System.err.println("Error al insertar el auto: " + e.getMessage());
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }
    
}
