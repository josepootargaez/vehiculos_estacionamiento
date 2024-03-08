package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento_vehiculos.models.Pagos;
import com.estacionamiento.estacionamiento_vehiculos.services.PaymentService;

@RestController
@Validated
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService payService;

    @GetMapping()
    public List<Pagos> getAllPayments(){
        return payService.getAllPayments();
    }
}
