package com.mahendracandi.poselectricityapp.controllers;

import com.mahendracandi.poselectricityapp.dtos.UserDto;
import com.mahendracandi.poselectricityapp.dtos.UserRequestDto;
import com.mahendracandi.poselectricityapp.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
@OpenAPIDefinition(info = @Info(
        title = "User API",
        description = "API to manage Users data",
        version = "1.0.0"))
@Tag(name = "User Management")
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create new User")
    @ApiResponse(responseCode = "200", description = "Successfully create User")
    public UserDto createUser(@RequestBody UserRequestDto requestDto) {
        final var user = userService.createUser(requestDto.getUsername(), requestDto.getPassword(), requestDto.getHakAkses());
        return new UserDto(user);
    }

    // todo retrieve user api
    // todo update user api
    // todo delete user api

}
