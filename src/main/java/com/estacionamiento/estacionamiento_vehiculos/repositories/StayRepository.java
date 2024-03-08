package com.estacionamiento.estacionamiento_vehiculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.estacionamiento_vehiculos.models.Estancia;


public interface StayRepository extends  JpaRepository<Estancia,Long>{
    Estancia findByplaca(String plaqueCar);
}
