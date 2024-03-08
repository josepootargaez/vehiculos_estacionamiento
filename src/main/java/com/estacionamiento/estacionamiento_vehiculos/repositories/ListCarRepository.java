package com.estacionamiento.estacionamiento_vehiculos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.estacionamiento_vehiculos.models.Catalogo_Autos;

public interface ListCarRepository extends JpaRepository<Catalogo_Autos,Long> {
    Catalogo_Autos findBytipoAuto(String tipo_auto);
}
