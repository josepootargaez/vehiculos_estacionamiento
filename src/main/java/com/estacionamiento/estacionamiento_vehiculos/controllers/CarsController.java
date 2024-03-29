package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento_vehiculos.dto.CarDTO;
import com.estacionamiento.estacionamiento_vehiculos.services.CarService;
import com.estacionamiento.estacionamiento_vehiculos.services.ValidationErrorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarService carService;
    
    @Autowired
    private ValidationErrorService errorService;

    @GetMapping()
    public List<?> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping()
    public ResponseEntity<?> insertCar( @Valid @RequestBody CarDTO carDTO, BindingResult bindingResult) {
        ResponseEntity<?> validationErrors = errorService.handleValidationErrors(bindingResult);
        if (validationErrors != null) {
            return validationErrors;
        }
        return carService.insertCar(carDTO);
        
    }
    
}
