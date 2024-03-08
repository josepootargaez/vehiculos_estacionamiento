package com.estacionamiento.estacionamiento_vehiculos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;
import com.estacionamiento.estacionamiento_vehiculos.repositories.StayRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StayService {
    
    @Autowired
    private StayRepository stayRepository;

    public List<Estancia> getAllStay(){
        return stayRepository.findAll();
    }
    
}
