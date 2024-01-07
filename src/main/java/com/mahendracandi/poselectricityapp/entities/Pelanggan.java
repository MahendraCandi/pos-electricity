package com.mahendracandi.poselectricityapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pelanggan")
public class Pelanggan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer pelangganId;
    private Integer tarifListrikId;
    private String noMeter;
    private String nama;

    @Column(columnDefinition = "TEXT")
    private String alamat;

}
