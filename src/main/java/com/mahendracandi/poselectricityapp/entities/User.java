package com.mahendracandi.poselectricityapp.entities;

import com.mahendracandi.poselectricityapp.enums.HakAkses;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userId;
    private String username;
    @Column(columnDefinition = "TEXT")
    private String password;
    private HakAkses hakAkses;
    private LocalDateTime createdDate;

}
