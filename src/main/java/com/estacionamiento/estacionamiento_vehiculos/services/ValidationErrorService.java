package com.estacionamiento.estacionamiento_vehiculos.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ValidationErrorService {

    public ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> responseBody = new HashMap<>();
            Map<String, Object> responseBodyBYerror = new HashMap<>();
            responseBody.put("success", false);
            int index=0;
            for (FieldError error : bindingResult.getFieldErrors()) {
                index++;
                responseBodyBYerror.put("validacion_"+index, error.getDefaultMessage());
            }
            responseBody.put("errores", responseBodyBYerror);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        return null;
    }

}
