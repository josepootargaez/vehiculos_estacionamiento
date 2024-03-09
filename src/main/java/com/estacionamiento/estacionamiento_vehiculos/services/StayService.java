package com.estacionamiento.estacionamiento_vehiculos.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento_vehiculos.dto.StayDTO;
import com.estacionamiento.estacionamiento_vehiculos.dto.StayOutDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Autos;
import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;
import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;
import com.estacionamiento.estacionamiento_vehiculos.models.Pagos;
import com.estacionamiento.estacionamiento_vehiculos.repositories.CarRepository;
import com.estacionamiento.estacionamiento_vehiculos.repositories.ListCarRepository;
import com.estacionamiento.estacionamiento_vehiculos.repositories.PaymentRepository;
import com.estacionamiento.estacionamiento_vehiculos.repositories.StayRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StayService {
    
    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private PaymentRepository payRepository;
    
    @Autowired
    private ListCarRepository listRepository;

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
            if(estancia.getFecha_salida() != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"La salida del vehiculo ya fue registrada anteriormente\",\"success\": false}");
            }
            estancia.setFecha_salida(LocalDateTime.now());
            Long idAuto = estancia.getIdAuto() != null ? estancia.getIdAuto() : 0; 
            Autos auto = carRepository.findById(idAuto)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró el auto"));
            Long idCatalogo = auto.getCatalogId() != null ? auto.getCatalogId() : 0; 
            Catalogo_Autos catalogo = listRepository.findById(idCatalogo)
            .orElseThrow(() ->  new IllegalArgumentException("No existe el tipo de auto"));
            String tipoAuto = catalogo.getTipoAuto();
            stayRepository.save(estancia);
            Pagos payment = new Pagos();
            Duration duration = Duration.between(estancia.getFecha_entrada(), estancia.getFecha_salida());
            long duracionEnMinutos = duration.toMinutes();
            double precio=0;
            if (!tipoAuto.equals("oficial")) {
                precio = duracionEnMinutos * 0.5;
            }
            payment.setMinutos(duracionEnMinutos);
            payment.setPrecio(precio);
            payment.setEstancia(estancia);
            payment.setTipo_vehiculo(catalogo);
            payRepository.save(payment);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("success", true);
            if (tipoAuto.equals("no residente")) {
                responseBody.put("data", payment);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
        } catch (Exception  e) {
            System.err.println("Error al insertar el auto: " + e.getMessage());
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }
    
}
