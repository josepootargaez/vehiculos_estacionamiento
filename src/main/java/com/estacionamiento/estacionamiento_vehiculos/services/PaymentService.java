package com.estacionamiento.estacionamiento_vehiculos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento_vehiculos.dto.PaymentDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;
import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;
import com.estacionamiento.estacionamiento_vehiculos.models.Pagos;
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

    public ResponseEntity<?> savePayment(PaymentDTO payDTO){
        Long idList = payDTO.getIdEstancia() != null ? payDTO.getIdEstancia() : 0; 
        Estancia list = stayRepository.findById(idList)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró el registro de entrada del auto"));
        Pagos pay = new Pagos();
        pay.setPrecio(payDTO.getPrecio());
        pay.setMinutos(payDTO.getMinutos());
        pay.setEstancia(list);
        payRepository.save(pay);
         return ResponseEntity.status(HttpStatus.CREATED).body("{\"success\": true}");
    }
    
}
