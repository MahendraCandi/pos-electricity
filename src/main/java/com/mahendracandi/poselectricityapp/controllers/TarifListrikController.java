package com.mahendracandi.poselectricityapp.controllers;

import com.mahendracandi.poselectricityapp.dtos.TarifListrikDto;
import com.mahendracandi.poselectricityapp.dtos.TarifListrikRequestDto;
import com.mahendracandi.poselectricityapp.services.TarifListrikService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api/v1/tarif-listrik")
@OpenAPIDefinition(info = @Info(
        title = "Tarif Listrik API",
        description = "API to manage Tarif Listrik data",
        version = "1.0.0"))
@Tag(name = "Tarif Listrik Management")
public class TarifListrikController {

    private final TarifListrikService tarifListrikService;

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create new Tarif Listrik")
    @ApiResponse(responseCode = "200", description = "Successfully create Tarif Listrik")
    public TarifListrikDto createTarifListrik(@RequestBody @Valid TarifListrikRequestDto requestDto) {
        final var tarifListrik = tarifListrikService.createTarifListrik(
                requestDto.getKodeTarif(), requestDto.getBeban(), requestDto.getTarifPerKwh(), requestDto.getCreatedBy()
        );

        return new TarifListrikDto(tarifListrik);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Find tarif listrik by parameters")
    @ApiResponse(responseCode = "200", description = "Successfully find tarif listrik")
    public List<TarifListrikDto> getTarifListrik(
            @RequestParam(name = "kodeTarif", required = false) String kodeTarif
    ) {
        return tarifListrikService.findTarifListrik(kodeTarif).stream()
                .map(TarifListrikDto::new)
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/{kodeTarif}", produces = "application/json")
    @Operation(summary = "Update tarif listrik by kode tarif")
    @ApiResponse(responseCode = "200", description = "Successfully update tarif listrik")
    public TarifListrikDto updateTarifListrik(
            @PathVariable("kodeTarif") String kodeTarif,
            @RequestBody TarifListrikRequestDto requestDto
    ) {
        final var tarifListrik = tarifListrikService.updateTarifListrik(kodeTarif, requestDto);
        return new TarifListrikDto(tarifListrik);
    }

    @DeleteMapping("/{kodeTarif}")
    @Operation(summary = "Delete tarif listrik by kode tarif")
    @ApiResponse(responseCode = "200", description = "Successfully delete tarif listrik")
    public void deleteTarifListrik(@PathVariable("kodeTarif") String kodeTarif) {
        tarifListrikService.deleteTarifListrik(kodeTarif);
    }
}
