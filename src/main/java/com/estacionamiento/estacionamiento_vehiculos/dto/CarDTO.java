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
    private Long tipo_auto_id;


    public Long getTipo_auto_id() {
        return tipo_auto_id;
    }


    public void setTipo_auto_id(Long tipo_auto_id) {
        this.tipo_auto_id = tipo_auto_id;
    }
   
   
}
