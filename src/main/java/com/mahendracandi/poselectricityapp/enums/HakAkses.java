package com.mahendracandi.poselectricityapp.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum HakAkses {
    ADMIN(1),
    SALES(2);

    private final Integer value;

    HakAkses(Integer value) {
        this.value = value;
    }

    public static HakAkses findByValue(Integer value) {
        return Arrays.stream(HakAkses.values())
                .filter(p -> Objects.equals(p.getValue(), value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error find Hak Akses")); // todo implement custom exception to print optimized error message
    }
}
