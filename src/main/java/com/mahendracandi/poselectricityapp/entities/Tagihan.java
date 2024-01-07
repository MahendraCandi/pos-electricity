package com.mahendracandi.poselectricityapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tagihan")
public class Tagihan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer tagihanId;
    private Integer pelangganId;
    private String tahunTagihan;
    private String bulanTagihan;
    private BigDecimal pemakaian;

}
