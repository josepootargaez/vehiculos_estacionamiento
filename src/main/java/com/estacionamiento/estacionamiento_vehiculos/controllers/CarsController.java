package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento_vehiculos.dto.CarDTO;
import com.estacionamiento.estacionamiento_vehiculos.services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@Validated
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarService carService;

    @GetMapping()
    public List<?> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping()
    public ResponseEntity<?> insertCar( @RequestBody CarDTO carDTO) {
        return carService.insertCar(carDTO);
        
    }
    
}
