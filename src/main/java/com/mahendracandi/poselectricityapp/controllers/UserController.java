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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(produces = "application/json")
    @Operation(summary = "Find users by parameters")
    @ApiResponse(responseCode = "200", description = "Successfully find users")
    public List<UserDto> getUser(@RequestParam(name = "username", required = false) String username) {
        return userService.findUsers(username).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/{username}", produces = "application/json")
    @Operation(summary = "Update a user by username")
    @ApiResponse(responseCode = "200", description = "Successfully update a user")
    public UserDto updateUser(@PathVariable(value = "username") String username,
                              @RequestBody UserRequestDto requestDto) {
        final var user = userService.updateUser(username, requestDto);
        return new UserDto(user);
    }

    @DeleteMapping(value = "/{username}", produces = "application/json")
    @Operation(summary = "Delete a user by username")
    @ApiResponse(responseCode = "200", description = "Successfully delete a user")
    public void deleteUser(@PathVariable(value = "username") String username) {
        userService.deleteUser(username);
    }

}
