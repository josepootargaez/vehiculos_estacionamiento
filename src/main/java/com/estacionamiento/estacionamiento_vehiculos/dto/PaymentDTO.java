package com.estacionamiento.estacionamiento_vehiculos.dto;


public class PaymentDTO {
    
    private Integer minutos;
    private double precio;
    private Long idEstancia;

    public Long getIdEstancia() {
        return idEstancia;
    }
    public void setIdEstancia(Long idEstancia) {
        this.idEstancia = idEstancia;
    }
    public Integer getMinutos() {
        return minutos;
    }
    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

  


}
