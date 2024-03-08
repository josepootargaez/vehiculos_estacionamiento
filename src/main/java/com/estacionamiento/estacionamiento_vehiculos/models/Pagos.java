package com.estacionamiento.estacionamiento_vehiculos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagos {
    
    private Integer minutos;
    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    private Double precio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return id;
    }
    
    @ManyToOne
    @JoinColumn(name = "estancia_id") // Nombre de la columna que será la clave foránea
    private Estancia estancia;
    public void setEstancia(Estancia estancia) {
        this.estancia = estancia;
    }
}
