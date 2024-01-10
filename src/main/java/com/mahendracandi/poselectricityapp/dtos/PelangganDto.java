package com.mahendracandi.poselectricityapp.dtos;

import com.mahendracandi.poselectricityapp.entities.Pelanggan;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PelangganDto {
    private final String noMeter;
    private final String nama;
    private final String alamat;
    private final String kodeTarif;
    private final BigDecimal beban;
    private final BigDecimal tarifPerKwh;

    public PelangganDto(Pelanggan pelanggan) {
        this.noMeter = pelanggan.getNoMeter();
        this.nama = pelanggan.getNama();
        this.alamat = pelanggan.getAlamat();

        final var tarifListrik = pelanggan.getTarifListrik();
        this.kodeTarif = tarifListrik.getKodeTarif();
        this.beban = tarifListrik.getBeban();
        this.tarifPerKwh = tarifListrik.getTarifPerkwh();
    }
}
