package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estacionamiento.estacionamiento_vehiculos.dto.ListCarDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;
import com.estacionamiento.estacionamiento_vehiculos.services.ListCarService;
import com.estacionamiento.estacionamiento_vehiculos.services.ValidationErrorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/list-cars")
public class ListCarController {

     @Autowired
    private ListCarService listCarService;

    @Autowired
    private ValidationErrorService errorService;

    @GetMapping()
    public List<Catalogo_Autos> getAllListCar(){
        return listCarService.getAllListCar();
    }

    @PostMapping()
    public ResponseEntity<?> insertCar(@Valid @RequestBody ListCarDTO data, BindingResult bindingResult ) {
        ResponseEntity<?> validationErrors = errorService.handleValidationErrors(bindingResult);
        if (validationErrors != null) {
            return validationErrors;
        }
        return listCarService.insertTypeCar(data);
        
    }
    
}
