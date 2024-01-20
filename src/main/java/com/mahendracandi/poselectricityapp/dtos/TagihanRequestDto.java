package com.mahendracandi.poselectricityapp.dtos;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TagihanRequestDto {
    private String noMeter;
    private String bulanTagihan;
    private String tahunTagihan;
    private BigDecimal totalPemakaian;
}
