package com.estacionamiento.estacionamiento_vehiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarDTO {
    @NotNull(message = "El campo placa_auto  no puede ser nulo")
    @NotBlank(message = "El campo placa_auto no puede estar vacía")
    private String placa_auto;
    
    
    public String getPlaca_auto() {
        return placa_auto;
    }


    public void setPlaca_auto(String placa_auto) {
        this.placa_auto = placa_auto;
    }

    @NotNull(message = "El campo catalogo_auto_id no puede ser nulo o vacio")
    private Long catalogo_auto_id;


    public Long getCatalogo_auto_id() {
        return catalogo_auto_id;
    }


    public void setCatalogo_auto_id(Long catalogo_auto_id) {
        this.catalogo_auto_id = catalogo_auto_id;
    }
   
   
}
