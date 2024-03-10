package com.estacionamiento.estacionamiento_vehiculos.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Optional<Estancia> estanciaFiltrada = Optional.empty();
            Autos auto = carRepository.findByplaca(stayDTO.getPlaca());
            if (auto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"La placa proporcionada no existe\",\"success\": false}");
            }
            List<Estancia> estanciaBusqueda = stayRepository.findByplaca(stayDTO.getPlaca());
            if (!estanciaBusqueda.isEmpty()) {
                estanciaFiltrada = estanciaBusqueda.stream()
                .filter(estancia -> estancia.getFecha_salida() == null)
                .findFirst();
            }
            
            if(estanciaFiltrada.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"Ya existe una entrada del vehiculo, registre la salida\",\"success\": false}");
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
            Optional<Estancia> estanciaFiltrada = Optional.empty();
            Optional<Estancia> estanciaFiltradaPagada = Optional.empty();
            Pagos pago = null;
            String placa = stayDTO.getPlaca() != null ? stayDTO.getPlaca() : null; 
            // Estancia estancia = stayRepository.findByplaca(placa);
            List<Estancia> estanciaBusqueda = stayRepository.findByplaca(placa);
            
            if (!estanciaBusqueda.isEmpty()) {
                estanciaFiltrada = estanciaBusqueda.stream()
                .filter(estancia -> estancia.getFecha_salida() == null)
                .findFirst();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"No existe la placa\",\"success\": false}");
            }
            if(!estanciaFiltrada.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"No existe entrada de la placa ingresada\",\"success\": false}");
            }else{
                Estancia estancia = estanciaFiltrada.get();
                estancia.setFecha_salida(LocalDateTime.now());
                Long idAuto = estancia.getIdAuto() != null ? estancia.getIdAuto() : 0; 
                Autos auto = carRepository.findById(idAuto)
                .orElseThrow(() ->  new IllegalArgumentException("No se encontró el auto"));
                Long idCatalogo = auto.getCatalogId() != null ? auto.getCatalogId() : 0; 
                Catalogo_Autos catalogo = listRepository.findById(idCatalogo)
                .orElseThrow(() ->  new IllegalArgumentException("No existe el tipo de auto"));
                String tipoAuto = catalogo.getTipoAuto();
                estanciaFiltradaPagada = estanciaBusqueda.stream()
                .filter(estanciaPagada -> estancia.getFecha_salida() != null)
                .findFirst();
                stayRepository.save(estancia);
                if(estanciaFiltradaPagada.isPresent()){
                    pago = payRepository.findByEstanciaId(estanciaFiltradaPagada.get().getId());
                }
                Duration duration = Duration.between(estancia.getFecha_entrada(), estancia.getFecha_salida());
                long duracionEnMinutos = duration.toMinutes();
                double precio=0;
                double pagoNoResidente = 0;
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("success", true);
                if (tipoAuto.equals("residente")) {
                    if(pago != null){
                        Long minutosAnteriores = pago.getMinutos();
                        Double pagoAnterior = pago.getPrecio();
                        precio = (duracionEnMinutos * 0.5) + pagoAnterior;
                        Long sumaMinutos = minutosAnteriores + duracionEnMinutos;
                        pago.setPrecio(precio);
                        pago.setMinutos(sumaMinutos);
                        payRepository.save(pago);
                    }
                }else{
                    Pagos payment = new Pagos();
                    if (!tipoAuto.equals("oficial")) {
                        precio = duracionEnMinutos * 0.5;
                    }
                    pagoNoResidente = precio;
                    payment.setMinutos(duracionEnMinutos);
                    payment.setPrecio(precio);
                    payment.setEstancia(estancia);
                    payment.setTipo_vehiculo(catalogo);
                    payRepository.save(payment);
                    if (tipoAuto.equals("no residente")) {
                        responseBody.put("importe", pagoNoResidente);
                    }
                }
                
                return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
            }
            
        } catch (Exception  e) {
            System.err.println("Error al insertar el auto: " + e.getMessage());
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }

    public ResponseEntity<?> startMonth() {
        LocalDateTime now = LocalDateTime.now();
        String mesActualStr = now.format(DateTimeFormatter.ofPattern("MM"));
        int mesActual = Integer.parseInt(mesActualStr);
        List<Estancia> estancias = stayRepository.findAll();
        List<Estancia> estanciasMesActual = estancias.stream()
        .filter(estancia -> estancia.getFecha_entrada().getMonthValue() == mesActual)
        .collect(Collectors.toList());
        
        estanciasMesActual.stream().forEach(estancia -> {
            Long idAuto = estancia.getIdAuto() != null ? estancia.getIdAuto() : 0;
            Autos auto = carRepository.findById(idAuto)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el auto"));
            Long idCatalogo = auto.getCatalogId() != null ? auto.getCatalogId() : 0;
            Catalogo_Autos catalogo = listRepository.findById(idCatalogo)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el tipo de auto"));
            if (catalogo != null) {
                Long idEstancia = estancia.getId() != null ? estancia.getId() : 0;
                Pagos pago = payRepository.findByEstanciaId(idEstancia);
                Long idPago = pago.getId() != null ? pago.getId() : 0;
        
                if (catalogo.getTipoAuto().equals("oficial")) {
                    payRepository.deleteById(idPago);
                    stayRepository.deleteById(idEstancia);
                } else if (catalogo.getTipoAuto().equals("residente")) {
                    pago.setMinutos((long) 0);
                    payRepository.save(pago);
                }
            }
        });
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", true);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
}
