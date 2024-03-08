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
import com.estacionamiento.estacionamiento_vehiculos.dto.ListCarDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;
import com.estacionamiento.estacionamiento_vehiculos.services.ListCarService;

@RestController
@Validated
@RequestMapping("/list-cars")
public class ListCarController {

     @Autowired
    private ListCarService listCarService;

    @GetMapping()
    public List<Catalogo_Autos> getAllListCar(){
        return listCarService.getAllListCar();
    }

    @PostMapping()
    public ResponseEntity<?> insertCar( @RequestBody ListCarDTO data ) {
        return listCarService.insertTypeCar(data);
        
    }
    
}
