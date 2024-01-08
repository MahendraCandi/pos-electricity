package com.mahendracandi.poselectricityapp.dtos;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TarifListrikRequestDto {
    private String kodeTarif;
    private BigDecimal beban;
    private BigDecimal tarifPerKwh;
    private String createdBy;
}
