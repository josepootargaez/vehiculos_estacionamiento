package com.estacionamiento.estacionamiento_vehiculos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento_vehiculos.dto.PaymentDTO;
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
public class PaymentService {

    @Autowired
    private PaymentRepository payRepository;

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private ListCarRepository listRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Pagos> getAllPayments(){
        return payRepository.findAll();
    }

    public ResponseEntity<?> savePayment(PaymentDTO payDTO){
        Long idList = payDTO.getIdEstancia() != null ? payDTO.getIdEstancia() : 0; 
        Long idAuto = payDTO.getIdAuto() != null ? payDTO.getIdAuto() : 0; 
        Estancia list = stayRepository.findById(idList)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró el registro de entrada del auto"));
        Autos listAuto = carRepository.findById(idAuto)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró el auto"));
        Long idCatalogo = listAuto.getCatalogId() != null ?listAuto.getCatalogId() : 0; 
        Catalogo_Autos catalogo = listRepository.findById(idCatalogo)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró el auto"));
        Pagos pay = new Pagos();
        pay.setPrecio(payDTO.getPrecio());
        pay.setMinutos(payDTO.getMinutos());
        pay.setEstancia(list);
        pay.setTipo_vehiculo(catalogo);
        payRepository.save(pay);
         return ResponseEntity.status(HttpStatus.CREATED).body("{\"success\": true}");
    }
    
}
