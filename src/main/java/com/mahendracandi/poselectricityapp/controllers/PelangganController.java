package com.mahendracandi.poselectricityapp.controllers;

import com.mahendracandi.poselectricityapp.dtos.PelangganDto;
import com.mahendracandi.poselectricityapp.dtos.PelangganRequestDto;
import com.mahendracandi.poselectricityapp.services.PelangganService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api/v1/pelanggan")
@OpenAPIDefinition(info = @Info(
        title = "Pelanggan Management API",
        description = "API to manage Pelanggan data",
        version = "1.0.0"))
@Tag(name = "Pelanggan Management")
public class PelangganController {

    private final PelangganService pelangganService;

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create new pelanggan")
    @ApiResponse(responseCode = "200", description = "Successfully create pelanggan")
    public PelangganDto createPelanggan(@RequestBody PelangganRequestDto requestDto) {
        final var pelanggan = pelangganService.createPelanggan(
                requestDto.getNama(), requestDto.getNoMeter(), requestDto.getKodeTarif(), requestDto.getAlamat());
        return new PelangganDto(pelanggan);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Find pelanggan")
    @ApiResponse(responseCode = "200", description = "Successfully find pelanggan")
    public List<PelangganDto> findPelanggan(
            @RequestParam(value = "noMeter",required = false) String noMeter,
            @RequestParam(value = "nama", required = false) String nama
    ) {
        final var listPelanggan = pelangganService.findPelanggan(noMeter, nama);
        return listPelanggan.stream()
                .map(PelangganDto::new)
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/{noMeter}", produces = "application/json")
    @Operation(summary = "Update pelanggan")
    @ApiResponse(responseCode = "200", description = "Successfully update pelanggan")
    public PelangganDto updatePelanggan(
            @PathVariable("noMeter") String noMeter,
            @RequestBody PelangganRequestDto requestDto
    ) {
        final var pelanggan = pelangganService.updatePelanggan(noMeter, requestDto);
        return new PelangganDto(pelanggan);
    }

    @DeleteMapping("/{noMeter}")
    @Operation(summary = "Delete pelanggan by no meter")
    @ApiResponse(responseCode = "200", description = "Successfully delete by no meter")
    public void deletePelanggan(@PathVariable("noMeter") String noMeter) {
        pelangganService.deletePelanggan(noMeter);
    }

}
