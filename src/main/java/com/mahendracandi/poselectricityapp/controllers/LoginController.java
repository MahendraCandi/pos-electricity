package com.mahendracandi.poselectricityapp.controllers;

import com.mahendracandi.poselectricityapp.dtos.LoginInfoDto;
import com.mahendracandi.poselectricityapp.dtos.LoginRequestDto;
import com.mahendracandi.poselectricityapp.dtos.LogoutRequestDto;
import com.mahendracandi.poselectricityapp.services.LoginService;
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
@RequestMapping(value = "/api/v1")
@OpenAPIDefinition(info = @Info(
        title = "Login API",
        description = "API to manage login",
        version = "1.0.0"))
@Tag(name = "Login Management")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login", produces = "application/json")
    @Operation(summary = "Login user by username")
    @ApiResponse(responseCode = "200", description = "Successfully login a user")
    public LoginInfoDto login(@RequestBody LoginRequestDto requestDto) {
        return loginService.login(requestDto);
    }

    @PostMapping(value = "/logout", produces = "application/json")
    @Operation(summary = "Logout user by username")
    @ApiResponse(responseCode = "200", description = "Successfully logout a user")
    public LoginInfoDto logout(@RequestBody LogoutRequestDto requestDto) {
        return loginService.logout(requestDto);
    }
}
