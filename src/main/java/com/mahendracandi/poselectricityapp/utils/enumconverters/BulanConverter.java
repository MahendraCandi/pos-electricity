package com.mahendracandi.poselectricityapp.utils.enumconverters;

import com.mahendracandi.poselectricityapp.enums.Bulan;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BulanConverter implements AttributeConverter<Bulan, String> {

    @Override
    public String convertToDatabaseColumn(Bulan attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getValue();
    }

    @Override
    public Bulan convertToEntityAttribute(String dbData) {
        return Bulan.findByValue(dbData);
    }
}
