package com.bankInc.creditcard.repositories;

import com.bankInc.creditcard.models.CreditCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICreditCard extends CrudRepository<CreditCard, Long> {

    @Query(
            value = "SELECT * FROM credit_card WHERE name = :name AND number = :number AND cvc = :cvc",
            nativeQuery = true)
    Optional<CreditCard> findCard(@Param("name") String name, @Param("number") Long number, @Param("cvc") int cvc);
}
