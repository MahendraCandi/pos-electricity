package com.mahendracandi.poselectricityapp.dtos;

import com.mahendracandi.poselectricityapp.entities.Tagihan;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TagihanDto {
    private final Integer idTagihan;
    private final String noMeter;
    private final String nama;
    private final String bulanTagihan;
    private final String bulanTagihanName;
    private final String tahunTagihan;
    private final BigDecimal totalPemakaian;

    public TagihanDto(Tagihan tagihan) {
        this.idTagihan = tagihan.getTagihanId();
        this.noMeter = tagihan.getPelanggan().getNoMeter();
        this.nama = tagihan.getPelanggan().getNama();
        this.tahunTagihan = tagihan.getTahunTagihan();
        this.bulanTagihan = tagihan.getBulanTagihan().getValue();
        this.bulanTagihanName = tagihan.getBulanTagihan().name();
        this.totalPemakaian = tagihan.getPemakaian();
    }
}
