package com.estacionamiento.estacionamiento_vehiculos.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estancia {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private String placa;
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }
    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }
    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }
    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
    @ManyToOne
    @JoinColumn(name = "auto_id") // Nombre de la columna que será la clave foránea
    private Autos autos;
    
    public void setAutos(Autos autos) {
        this.autos = autos;
    }

    public Long getIdAuto() {
        return autos.getId();
    }

    

}
