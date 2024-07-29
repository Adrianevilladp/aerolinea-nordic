package com.aerovik.nordic.dto;

import com.aerovik.nordic.enums.ParkingQuality;
import com.aerovik.nordic.enums.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
public class ParkingDetailsDTO {
    private String name;
    private String price;
    private String quality;
    private String type;

    public ParkingDetailsDTO(String name, Currency currency,
                             BigDecimal price, ParkingQuality quality, ParkingType type) {
        this.name = name;
        this.price = String.format("%s %s", currency, price);
        this.quality = quality.name();
        this.type = type.name();
    }
}
