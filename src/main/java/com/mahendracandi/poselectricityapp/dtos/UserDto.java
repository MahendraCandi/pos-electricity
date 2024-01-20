package com.mahendracandi.poselectricityapp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mahendracandi.poselectricityapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {
    private Integer userId;
    private String username;
    private String hakAkses;
    private Integer hakAksesValue;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.hakAkses = user.getHakAkses().name();
        this.hakAksesValue = user.getHakAkses().getValue();
        this.createdDate = user.getCreatedDate();
    }
}
