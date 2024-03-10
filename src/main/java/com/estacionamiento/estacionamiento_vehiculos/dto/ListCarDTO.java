package com.estacionamiento.estacionamiento_vehiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ListCarDTO {

    @NotNull(message = "El campo tipo_auto  no puede ser nulo")
    @NotBlank(message = "El campo tipo_auto no puede estar vacía")
    private String tipo_auto;

    public String getTipo_auto() {
        return tipo_auto;
    }

    public void setTipo_auto(String tipo_auto) {
        this.tipo_auto = tipo_auto;
    }
}
