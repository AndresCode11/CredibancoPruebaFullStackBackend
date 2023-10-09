package com.bankInc.creditcard.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull
    private Long number;

    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expire;

    @Column(columnDefinition = "bigint default 0")
    private Long balance;

    @NotNull
    private int cvc;

    private Date createdAt;

}
