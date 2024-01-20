package com.mahendracandi.poselectricityapp.services;

import com.mahendracandi.poselectricityapp.dtos.PelangganRequestDto;
import com.mahendracandi.poselectricityapp.entities.Pelanggan;
import com.mahendracandi.poselectricityapp.repositories.PelangganRepository;
import com.mahendracandi.poselectricityapp.repositories.TarifListrikRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PelangganService {

    private final PelangganRepository pelangganRepository;
    private final TarifListrikRepository tarifListrikRepository;

    public PelangganService(PelangganRepository pelangganRepository, TarifListrikRepository tarifListrikRepository) {
        this.pelangganRepository = pelangganRepository;
        this.tarifListrikRepository = tarifListrikRepository;
    }

    public Pelanggan createPelanggan(String nama, String noMeter, String kodeTarif, String alamat) {
        final var tarifListrik = tarifListrikRepository.findByKodeTarif(kodeTarif)
                .orElseThrow(() -> new IllegalArgumentException("Kode Tarif not found")); // todo update to better exception

        return pelangganRepository.save(Pelanggan.builder()
                .nama(nama)
                .noMeter(noMeter)
                .alamat(alamat)
                .tarifListrik(tarifListrik)
                .build());
    }

    public List<Pelanggan> findPelanggan(String noMeter, String nama) {
        return pelangganRepository.findAllByNoMeterAndNama(noMeter, nama);
    }

    public Pelanggan updatePelanggan(String noMeter, PelangganRequestDto requestDto) {
        var pelanggan = pelangganRepository.findPelangganByNoMeter(noMeter)
                .orElseThrow(() -> new IllegalArgumentException("Pelanggan not found")); // todo update to better exception

        if (StringUtils.isNotBlank(requestDto.getNoMeter())) {
            pelanggan.setNoMeter(requestDto.getNoMeter());
        }

        if (StringUtils.isNotBlank(requestDto.getKodeTarif())) {
            final var tarifListrik = tarifListrikRepository.findByKodeTarif(requestDto.getKodeTarif())
                    .orElseThrow(() -> new IllegalArgumentException("Kode Tarif not found")); // todo update to better exception
            pelanggan.setTarifListrik(tarifListrik);
        }

        if (StringUtils.isNotBlank(requestDto.getNama())) {
            pelanggan.setNama(requestDto.getNama());
        }

        if (StringUtils.isNotBlank(requestDto.getAlamat())) {
            pelanggan.setAlamat(requestDto.getAlamat());
        }

        return pelangganRepository.save(pelanggan);
    }

    public void deletePelanggan(String noMeter) {
        var pelanggan = pelangganRepository.findPelangganByNoMeter(noMeter)
                .orElseThrow(() -> new IllegalArgumentException("Pelanggan not found")); // todo update to better exception
        pelangganRepository.delete(pelanggan);
    }
}
