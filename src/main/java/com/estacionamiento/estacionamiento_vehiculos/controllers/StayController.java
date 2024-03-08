package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;
import com.estacionamiento.estacionamiento_vehiculos.services.StayService;

@RestController
@Validated
public class StayController {
    
    @Autowired
    private StayService stayService;

    @GetMapping("/stay")
    public List<Estancia> getAllstays(){
        return stayService.getAllStay();
    }

    // @PostMapping("/cars")
    // public ResponseEntity<?> insertCar( @RequestBody CarDTO carDTO) {
    //     return stayService.insertCar(carDTO);
        
    // }

}
