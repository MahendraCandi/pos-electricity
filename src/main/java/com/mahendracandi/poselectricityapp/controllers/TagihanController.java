package com.mahendracandi.poselectricityapp.controllers;

import com.mahendracandi.poselectricityapp.dtos.TagihanDto;
import com.mahendracandi.poselectricityapp.dtos.TagihanRequestDto;
import com.mahendracandi.poselectricityapp.services.TagihanService;
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
@RequestMapping(value = "/api/v1/tagihan")
@OpenAPIDefinition(info = @Info(
        title = "Tagihan Management API",
        description = "API to manage Tagihan data",
        version = "1.0.0"))
@Tag(name = "Tagihan Management")
public class TagihanController {

    private final TagihanService tagihanService;

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create new tagihan")
    @ApiResponse(responseCode = "200", description = "Successfully create tagihan")
    public TagihanDto createTagihan(@RequestBody TagihanRequestDto requestDto) {
        final var tagihan = tagihanService.createTagihan(requestDto.getNoMeter(), requestDto.getBulanTagihan(), requestDto.getTahunTagihan(), requestDto.getTotalPemakaian());
        return new TagihanDto(tagihan);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Find tagihan")
    @ApiResponse(responseCode = "200", description = "Successfully find tagihan")
    public List<TagihanDto> findPelanggan(
            @RequestParam(value = "noMeter",required = false) String noMeter,
            @RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "bulan", required = false) String bulan,
            @RequestParam(value = "tahun", required = false) String tahun
    ) {
        final var tagihanList = tagihanService.findTagihan(noMeter, nama, bulan, tahun);
        return tagihanList.stream()
                .map(TagihanDto::new)
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/{idTagihan}", produces = "application/json")
    @Operation(summary = "Update tagihan")
    @ApiResponse(responseCode = "200", description = "Successfully update tagihan")
    public TagihanDto updateTagihan(
            @PathVariable("idTagihan") Integer idTagihan,
            @RequestBody TagihanRequestDto requestDto
    ) {
        final var tagihan = tagihanService.updateTagihan(idTagihan, requestDto);
        return new TagihanDto(tagihan);
    }

    @DeleteMapping(value = "/{idTagihan}")
    @Operation(summary = "Delete tagihan by id")
    @ApiResponse(responseCode = "200", description = "Successfully delete by id")
    public void deleteTagihan(@PathVariable("idTagihan") Integer idTagihan) {
        tagihanService.deleteTagihan(idTagihan);
    }
}
