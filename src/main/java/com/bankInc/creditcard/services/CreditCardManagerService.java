package com.bankInc.creditcard.services;

import com.bankInc.creditcard.models.CreditCard;
import com.bankInc.creditcard.repositories.ICreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardManagerService {

    @Autowired
    ICreditCard creditCardRepository;

    @Transactional
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Transactional
    public ResponseEntity<?> discountCreditCard(CreditCard creditCard) {
        Optional<CreditCard> card = creditCardRepository.findCard(creditCard.getName(), creditCard.getNumber(), creditCard.getCvc());
        if(card.isPresent()) {
            Long currentBalance = card.get().getBalance();

            /* Validamos si tenemos el saldo suficiente */
            if(currentBalance >= creditCard.getBalance()) {
                /* Realizar el descuento */
                card.get().setBalance(currentBalance - creditCard.getBalance());
                creditCardRepository.save(card.get());

                return ResponseEntity.ok()
                        .body(Collections
                                .singletonMap("response", "Transaccion realizada de manera exitosa"));
            } else {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("response", "No Cuentas con el saldo suficiente"));
            }
        } else {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("response", "No Existe una tarjeta con esos datos"));
        }
    }

    @Transactional(readOnly = true)
    public List<CreditCard> getCreditCards() {
        return (List<CreditCard>) creditCardRepository.findAll();
    }
}
