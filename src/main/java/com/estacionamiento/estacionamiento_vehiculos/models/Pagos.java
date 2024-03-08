package com.estacionamiento.estacionamiento_vehiculos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagos {
    
    private Long minutos;
    public Long getMinutos() {
        return minutos;
    }

    public void setMinutos(Long minutos) {
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

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id") // Nombre de la columna que será la clave foránea
    private Catalogo_Autos tipo_vehiculo;
    public void setTipo_vehiculo(Catalogo_Autos tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public Long getIdEstancia(){
        return estancia.getId();
    }

    public Long getIdAuto(){
        return estancia.getIdAuto();
    }
    
    public String getTipoAuto(){
        return tipo_vehiculo.getTipoAuto();
    }
 
}
