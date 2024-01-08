package com.mahendracandi.poselectricityapp.services;

import com.mahendracandi.poselectricityapp.entities.TarifListrik;
import com.mahendracandi.poselectricityapp.repositories.TarifListrikRepository;
import com.mahendracandi.poselectricityapp.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarifListrikService {

    private final TarifListrikRepository tarifListrikRepository;
    private final UserRepository userRepository;

    public TarifListrikService(TarifListrikRepository tarifListrikRepository, UserRepository userRepository) {
        this.tarifListrikRepository = tarifListrikRepository;
        this.userRepository = userRepository;
    }

    public TarifListrik createTarifListrik(String kodeTarif, BigDecimal beban, BigDecimal tarifPerKwh, String username) {
        final var user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found")); // todo improve exception

        if (kodeTarif.length() > 4) {
            throw new IllegalArgumentException("kodeTarif should not more than 4 characters");
        }

        return tarifListrikRepository.save(TarifListrik.builder()
                .kodeTarif(kodeTarif)
                .beban(beban)
                .tarifPerkwh(tarifPerKwh)
                .createdBy(user)
                .createdDate(LocalDateTime.now())
                .build());
    }

    public List<TarifListrik> findTarifListrik(String kodeTarif) {
        if (StringUtils.isBlank(kodeTarif)) {
            return tarifListrikRepository.findAll();
        }

        return tarifListrikRepository.findAllByKodeTarifStartsWith(kodeTarif);
    }
}
