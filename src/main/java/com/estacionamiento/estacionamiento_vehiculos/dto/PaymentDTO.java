package com.estacionamiento.estacionamiento_vehiculos.dto;


public class PaymentDTO {
    
    private Long minutos;
    private double precio;
    private Long idEstancia;
    private Long idAuto;

    public Long getIdAuto() {
        return idAuto;
    }
    public void setIdAuto(Long idAuto) {
        this.idAuto = idAuto;
    }
    public Long getIdEstancia() {
        return idEstancia;
    }
    public void setIdEstancia(Long idEstancia) {
        this.idEstancia = idEstancia;
    }
    public Long getMinutos() {
        return minutos;
    }
    public void setMinutos(Long minutos) {
        this.minutos = minutos;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

  


}
