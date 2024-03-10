package com.estacionamiento.estacionamiento_vehiculos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;


public interface StayRepository extends  JpaRepository<Estancia,Long>{
    List <Estancia> findByplaca(String plaqueCar);
}
