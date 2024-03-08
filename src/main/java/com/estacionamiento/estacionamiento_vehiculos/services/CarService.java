package com.estacionamiento.estacionamiento_vehiculos.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento_vehiculos.dto.CarDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Autos;
import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;
import com.estacionamiento.estacionamiento_vehiculos.repositories.CarRepository;
import com.estacionamiento.estacionamiento_vehiculos.repositories.ListCarRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;
   
    @Autowired
    private ListCarRepository listCarRepository;

    public List<?> getAllCars(){
        return carRepository.findAll();
    }
    public ResponseEntity<?> insertCar(CarDTO carDTO){
        try {
            Long idList = carDTO.getTipo_auto_id() != null ? carDTO.getTipo_auto_id() : 1; 
            Catalogo_Autos list = listCarRepository.findById(idList)
            .orElseThrow(() ->  new IllegalArgumentException("No se encontró el catálogo de autos con el ID proporcionado"));
             Autos autoExistente = carRepository.findByplaca(carDTO.getPlaca_auto());
            if (autoExistente != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("{\"error\": \"La placa ya está registrada\",\"success\": false}");
            }
            Autos car = new Autos();
            car.setPlaca(carDTO.getPlaca_auto());
            car.setCatalogoAutos(list);
            carRepository.save(car);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"success\": true}");
        } catch (Exception  e) {
            System.err.println("Error al insertar el auto: " + e.getMessage());
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
        
    }
    
}
