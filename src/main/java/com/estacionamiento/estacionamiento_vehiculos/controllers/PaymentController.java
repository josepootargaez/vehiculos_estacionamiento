package com.estacionamiento.estacionamiento_vehiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento_vehiculos.dto.ReportDTO;
import com.estacionamiento.estacionamiento_vehiculos.models.Pagos;
import com.estacionamiento.estacionamiento_vehiculos.services.PaymentService;
import com.estacionamiento.estacionamiento_vehiculos.services.ValidationErrorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService payService;

    @Autowired
    private ValidationErrorService errorService;

    @GetMapping()
    public List<Pagos> getAllPayments(){
        return payService.getAllPayments();
    }

    @PostMapping("/report")
    public ResponseEntity<?> getReport(@Valid @RequestBody ReportDTO reportDTO, BindingResult bindingResult){
        ResponseEntity<?> validationErrors = errorService.handleValidationErrors(bindingResult);
        if (validationErrors != null) {
            return validationErrors;
        }
        return payService.exportarDatos(reportDTO);
    }
}
