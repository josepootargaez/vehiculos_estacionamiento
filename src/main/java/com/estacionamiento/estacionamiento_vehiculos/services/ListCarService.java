package com.estacionamiento.estacionamiento_vehiculos.services;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento_vehiculos.dto.ListCarDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;
import com.estacionamiento.estacionamiento_vehiculos.repositories.ListCarRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ListCarService {

    @Autowired
    private ListCarRepository listRepository;

    public List<Catalogo_Autos> getAllListCar(){
        return listRepository.findAll();
    }

    public ResponseEntity<?> insertTypeCar(ListCarDTO data){
        try {
            Catalogo_Autos auto = listRepository.findBytipoAuto(data.getTipo_auto());
            if (auto != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("{\"error\": \"el tipo de auto ya está registrado\",\"success\": false}");
            }
            Catalogo_Autos catalogo = new Catalogo_Autos();
            catalogo.setTipoAuto(data.getTipo_auto());
            listRepository.save(catalogo);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"success\": true}");
        } catch (Exception  e) {
            System.err.println("Error al insertar el auto: " + e.getMessage());
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
        
    }

    @EventListener
    public void inicializarCatalogo(ApplicationReadyEvent event) {
        if (listRepository.count() == 0) {
            String[] list ={"oficial","residente","no residente"};
            Arrays.stream(list).forEach(tipo ->{
                Catalogo_Autos catalogo = new Catalogo_Autos();
                catalogo.setTipoAuto(tipo);
                listRepository.save(catalogo);

            });
        }
    }
    
}
