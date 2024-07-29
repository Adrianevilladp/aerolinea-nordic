package com.aerovik.nordic.enums;

public enum BookingType {
    FIRST_CLASS,
    BUSINESS_CLASS,
    ECONOMY_CLASS;

    public static BookingType fromString(String bookingType) {
        return switch (bookingType) {
            case "FIRST_CLASS" -> FIRST_CLASS;
            case "BUSINESS_CLASS" -> BUSINESS_CLASS;
            case "ECONOMY_CLASS" -> ECONOMY_CLASS;
            default -> throw new IllegalArgumentException("Unexpected value: " + bookingType);
        };
    }

}
