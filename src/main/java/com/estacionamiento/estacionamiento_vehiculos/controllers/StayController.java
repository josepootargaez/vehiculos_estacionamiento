package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento_vehiculos.dto.StayDTO;
import com.estacionamiento.estacionamiento_vehiculos.dto.StayOutDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;
import com.estacionamiento.estacionamiento_vehiculos.services.StayService;

@RestController
@Validated
@RequestMapping("/stay")
public class StayController {
    
    @Autowired
    private StayService stayService;

    @GetMapping()
    public List<Estancia> getAllstays(){
        return stayService.getAllStay();
    }

    @PostMapping("/input")
    public ResponseEntity<?> insertStay( @RequestBody StayDTO carDTO) {
        return stayService.insertStay(carDTO);
        
    }

    @PostMapping("/output")
    public ResponseEntity<?> insertOutPut( @RequestBody StayOutDTO carDTO) {
        return stayService.insertOut(carDTO);
        
    }

    @GetMapping("/start-month")
    public ResponseEntity<?> setStay(){
        return stayService.startMonth();
    }

}
