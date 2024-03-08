package com.estacionamiento.estacionamiento_vehiculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.estacionamiento_vehiculos.models.Autos;

public interface CarRepository extends JpaRepository<Autos,Long> {
    Autos findByplaca(String plaqueCar);
}