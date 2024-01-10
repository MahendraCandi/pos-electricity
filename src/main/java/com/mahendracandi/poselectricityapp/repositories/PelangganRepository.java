package com.mahendracandi.poselectricityapp.repositories;

import com.mahendracandi.poselectricityapp.entities.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PelangganRepository extends JpaRepository<Pelanggan, Integer> {

    @Query(value = "select p from Pelanggan p where " +
            "(p.noMeter like :noMeter% or :noMeter is null) and " +
            "(p.nama like :nama% or :nama is null)")
    List<Pelanggan> findAllByNoMeterAndNama(@Param("noMeter") String noMeter, @Param("nama") String nama);

    Optional<Pelanggan> findPelangganByNoMeter(String noMeter);
}
