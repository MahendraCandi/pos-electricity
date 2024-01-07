package com.mahendracandi.poselectricityapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tarif_listrik")
public class TarifListrik {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer tarifListrikId;
    private Integer userId;
    private String kodeTarif;
    private BigDecimal beban;
    private BigDecimal tarifPerkwh;

}
