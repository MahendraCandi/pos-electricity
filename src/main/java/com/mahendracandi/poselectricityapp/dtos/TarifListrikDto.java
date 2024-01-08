package com.mahendracandi.poselectricityapp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mahendracandi.poselectricityapp.entities.TarifListrik;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class TarifListrikDto {
    private String kodeTarif;
    private BigDecimal beban;
    private BigDecimal tarifPerKwh;
    private String createdBy;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

    public TarifListrikDto(TarifListrik tarifListrik) {
        this.kodeTarif = tarifListrik.getKodeTarif();
        this.beban = tarifListrik.getBeban();
        this.createdBy = tarifListrik.getCreatedBy().getUsername();
        this.tarifPerKwh = tarifListrik.getTarifPerkwh();
        this.createdDate = tarifListrik.getCreatedDate();
        this.updatedDate = tarifListrik.getUpdatedDate();
    }
}
