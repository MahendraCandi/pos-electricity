package com.mahendracandi.poselectricityapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarif_listrik")
public class TarifListrik {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer tarifListrikId;
    @Column(length = 4)
    private String kodeTarif;
    private BigDecimal beban;
    private BigDecimal tarifPerkwh;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
