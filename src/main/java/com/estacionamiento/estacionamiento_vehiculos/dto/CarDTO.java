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


    public Long getTipo_auto() {
        return tipo_auto;
    }


    public void setTipo_auto(Long tipo_auto) {
        this.tipo_auto = tipo_auto;
    }


    @NonNull
    private Long tipo_auto;
   
   
}
