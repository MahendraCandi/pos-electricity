package com.mahendracandi.poselectricityapp.services;

import com.mahendracandi.poselectricityapp.dtos.TagihanRequestDto;
import com.mahendracandi.poselectricityapp.entities.Tagihan;
import com.mahendracandi.poselectricityapp.enums.Bulan;
import com.mahendracandi.poselectricityapp.repositories.PelangganRepository;
import com.mahendracandi.poselectricityapp.repositories.TagihanRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TagihanService {

    private final PelangganRepository pelangganRepository;
    private final TagihanRepository tagihanRepository;

    public TagihanService(PelangganRepository pelangganRepository, TagihanRepository tagihanRepository) {
        this.pelangganRepository = pelangganRepository;
        this.tagihanRepository = tagihanRepository;
    }

    public Tagihan createTagihan(String noMeter, String bulan, String tahun, BigDecimal totalPemakaian) {
        var pelanggan = pelangganRepository.findPelangganByNoMeter(noMeter)
                .orElseThrow(() -> new IllegalArgumentException("Pelanggan not found")); // todo update to better exception

        return tagihanRepository.save(Tagihan.builder()
                .pelanggan(pelanggan)
                .bulanTagihan(Bulan.findByValue(bulan))
                .tahunTagihan(validateTahun(tahun))
                .pemakaian(totalPemakaian)
                .build());
    }

    public List<Tagihan> findTagihan(String noMeter, String nama, String bulan, String tahun) {
        return tagihanRepository.findAllByParameters(noMeter, nama, StringUtils.isEmpty(bulan) ? null : Bulan.findByValue(bulan), tahun);
    }

    public Tagihan updateTagihan(Integer idTagihan, TagihanRequestDto requestDto) {
        var tagihan = tagihanRepository.findById(idTagihan)
                .orElseThrow(() -> new IllegalArgumentException("Tagihan not found"));

        if (StringUtils.isNotEmpty(requestDto.getNoMeter())) {
            var pelanggan = pelangganRepository.findPelangganByNoMeter(requestDto.getNoMeter())
                    .orElseThrow(() -> new IllegalArgumentException("Pelanggan not found")); // todo update to better exception
            tagihan.setPelanggan(pelanggan);
        }

        if (StringUtils.isNotEmpty(requestDto.getBulanTagihan())) {
            tagihan.setBulanTagihan(Bulan.findByValue(requestDto.getBulanTagihan()));
        }

        if (StringUtils.isNotEmpty(requestDto.getTahunTagihan())) {
            tagihan.setTahunTagihan(validateTahun(requestDto.getTahunTagihan()));
        }

        if (Objects.nonNull(requestDto.getTotalPemakaian())) {
            tagihan.setPemakaian(requestDto.getTotalPemakaian());
        }

        return tagihanRepository.save(tagihan);
    }

    private String validateTahun(String tahun) {
        var maxYear = LocalDate.now().getYear() + 1;
        var inputYear = Integer.parseInt(tahun);

        if (inputYear > maxYear) {
            throw new IllegalArgumentException("Maximum Tahun should be current year plus one");
        }

        return tahun;
    }

    public void deleteTagihan(Integer idTagihan) {
        var tagihan = tagihanRepository.findById(idTagihan)
                .orElseThrow(() -> new IllegalArgumentException("Tagihan not found"));

        tagihanRepository.delete(tagihan);
    }
}
