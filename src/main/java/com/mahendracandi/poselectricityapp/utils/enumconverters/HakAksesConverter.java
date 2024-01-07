package com.mahendracandi.poselectricityapp.utils.enumconverters;

import com.mahendracandi.poselectricityapp.enums.HakAkses;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HakAksesConverter implements AttributeConverter<HakAkses, Integer> {

    @Override
    public Integer convertToDatabaseColumn(HakAkses attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getValue();
    }

    @Override
    public HakAkses convertToEntityAttribute(Integer dbData) {
        return HakAkses.findByValue(dbData);
    }
}
