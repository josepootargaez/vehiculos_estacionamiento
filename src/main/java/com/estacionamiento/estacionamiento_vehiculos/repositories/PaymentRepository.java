package com.estacionamiento.estacionamiento_vehiculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.estacionamiento_vehiculos.models.Pagos;

public interface PaymentRepository extends JpaRepository<Pagos,Long> {
    Pagos findByEstanciaId(Long estanciaId);
}
