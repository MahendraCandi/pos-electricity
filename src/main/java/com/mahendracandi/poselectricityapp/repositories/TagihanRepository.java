package com.mahendracandi.poselectricityapp.repositories;

import com.mahendracandi.poselectricityapp.entities.Tagihan;
import com.mahendracandi.poselectricityapp.enums.Bulan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagihanRepository extends JpaRepository<Tagihan, Integer> {
    @Query(
            value = "select t from Tagihan as t " +
                    "join Pelanggan p on t.pelanggan.pelangganId = p.pelangganId " +
                    "where " +
                    "(:noMeter is null or p.noMeter like :noMeter%) and " +
                    "(:nama is null or p.nama like :nama%) and " +
                    "(:bulan is null or t.bulanTagihan = :bulan) and " +
                    "(:tahun is null or t.tahunTagihan like :tahun)"
    )
    List<Tagihan> findAllByParameters(
            @Param("noMeter") String noMeter,
            @Param("nama") String nama,
            @Param("bulan") Bulan bulan,
            @Param("tahun") String tahun);
}
