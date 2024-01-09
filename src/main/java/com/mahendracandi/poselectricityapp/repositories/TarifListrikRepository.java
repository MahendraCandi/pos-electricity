package com.mahendracandi.poselectricityapp.repositories;

import com.mahendracandi.poselectricityapp.entities.TarifListrik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarifListrikRepository extends JpaRepository<TarifListrik, Integer> {
    List<TarifListrik> findAllByKodeTarifStartsWith(String kodeTarif);
    Optional<TarifListrik> findAllByKodeTarif(String kodeTarif);
}
