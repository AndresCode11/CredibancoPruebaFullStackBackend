package com.bankInc.creditcard.controllers;

import com.bankInc.creditcard.models.CreditCard;
import com.bankInc.creditcard.services.CreditCardManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/card")
@CrossOrigin(origins = "*")
public class CreditCardRest {

    @Autowired
    CreditCardManagerService creditCardManagerSerice;

    /* Retorna todas las tarjetas creadas */
    @GetMapping("")
    public ResponseEntity<List<CreditCard>> getCards() {
        return ResponseEntity.status(HttpStatus.OK).body((creditCardManagerSerice.getCreditCards()));
    }

    /* Crear tarjeta de credito */
    @PostMapping
    ResponseEntity<?> createCard(@Valid @RequestBody CreditCard creditCard, BindingResult result) {

        if (result.hasErrors()) {
            return validate(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardManagerSerice.saveCreditCard(creditCard));
    }

    /* Ejecuta una transaccion en la tarjeta (DESCUENTO) */
    @PostMapping("discount")
    @CrossOrigin(origins = "*")
    ResponseEntity<?> discountCard(@Valid @RequestBody CreditCard creditCard, BindingResult result) {

        if (result.hasErrors()) {
            return validate(result);
        }
        return creditCardManagerSerice.discountCreditCard(creditCard);
    }

    /* Imprime errores de entrada en validaciones */
    private ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


}
