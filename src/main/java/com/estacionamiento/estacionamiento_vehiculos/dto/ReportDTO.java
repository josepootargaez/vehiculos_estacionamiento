package com.estacionamiento.estacionamiento_vehiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReportDTO {

    @NotNull(message = "El campo nombreArchivo  no puede ser nulo")
    @NotBlank(message = "El campo nombreArchivo no puede estar vacía")
    @Pattern(regexp = "^[^.]*$", message = "El campo nombreArchivo no puede contener el carácter '.' ")
    private String nombreArchivo;

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
