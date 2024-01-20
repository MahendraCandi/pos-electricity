package com.mahendracandi.poselectricityapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Bulan {
    JANUARI("01"),
    FEBRUARI("02"),
    MARET("03"),
    APRIL("04"),
    MEI("05"),
    JUNI("06"),
    JULI("07"),
    AGUSTUS("08"),
    SEPTEMBER("09"),
    OKTOBER("10"),
    NOVEMBER("11"),
    DESEMBER("12");

    private final String value;

    public static Bulan findByValue(String value) {
        return Arrays.stream(Bulan.values()).filter(b -> b.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Bulan value should be 01 - 12"));
    }
}
