package com.estacionamiento.estacionamiento_vehiculos.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Autos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return id;
    }
    private String  placa;
    
    
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    @ManyToOne
    @JoinColumn(name = "catalogo_autos_id") // Nombre de la columna que será la clave foránea
    private Catalogo_Autos catalogoAutos;
 
    public void setCatalogoAutos(Catalogo_Autos catalogoAutos) {
        this.catalogoAutos = catalogoAutos;
    }
    public String getTipoAuto() {
        return catalogoAutos.getTipoAuto();
    }
    
}
