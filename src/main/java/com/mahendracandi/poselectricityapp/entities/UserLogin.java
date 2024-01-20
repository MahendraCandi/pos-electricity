package com.mahendracandi.poselectricityapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_login")
public class UserLogin {
    @Id
    private Integer userId;
    private Short isLogin;
    private LocalDateTime timestamp;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
}
