package com.estacionamiento.estacionamiento_vehiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StayDTO {
    
    @NotNull(message = "El campo placa  no puede ser nulo")
    @NotBlank(message = "El campo placa no puede estar vacía")
    String placa;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
