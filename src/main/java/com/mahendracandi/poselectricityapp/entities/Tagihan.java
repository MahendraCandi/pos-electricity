package com.mahendracandi.poselectricityapp.entities;

import com.mahendracandi.poselectricityapp.enums.Bulan;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tagihan")
public class Tagihan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer tagihanId;
    private String tahunTagihan;
    private Bulan bulanTagihan;
    private BigDecimal pemakaian;

    @ManyToOne
    @JoinColumn(name = "pelanggan_id")
    private Pelanggan pelanggan;

}
