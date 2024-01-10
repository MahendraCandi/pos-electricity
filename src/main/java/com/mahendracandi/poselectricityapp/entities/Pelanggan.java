package com.mahendracandi.poselectricityapp.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pelanggan")
public class Pelanggan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer pelangganId;
    private String noMeter;
    private String nama;

    @Column(columnDefinition = "TEXT")
    private String alamat;

    @ManyToOne
    @JoinColumn(name = "tarif_listrik_id")
    private TarifListrik tarifListrik;
}
