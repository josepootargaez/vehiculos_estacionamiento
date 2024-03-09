package com.estacionamiento.estacionamiento_vehiculos.dto;

import io.micrometer.common.lang.NonNull;


public class CarDTO {
    @NonNull
    private String placa_auto;
    
    
    public String getPlaca_auto() {
        return placa_auto;
    }


    public void setPlaca_auto(String placa_auto) {
        this.placa_auto = placa_auto;
    }


    @NonNull
    private Long catalogo_auto_id;


    public Long getCatalogo_auto_id() {
        return catalogo_auto_id;
    }


    public void setCatalogo_auto_id(Long catalogo_auto_id) {
        this.catalogo_auto_id = catalogo_auto_id;
    }
   
   
}
