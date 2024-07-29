package com.aerovik.nordic.dto;

import com.aerovik.nordic.dto.mapper.ParkingMapper;
import com.aerovik.nordic.dto.mapper.ShopMapper;
import com.aerovik.nordic.model.City;
import com.aerovik.nordic.model.Parking;
import com.aerovik.nordic.model.Shop;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AirportDetailsDTO {
    private String name;
    private String location;
    private String code;
    private Set<ShopDetailsDTO> shops;
    private Set<ParkingDetailsDTO> parkings;
    private static final ShopMapper shopMapper = new ShopMapper();
    private static final ParkingMapper parkingMapper = new ParkingMapper();

    public AirportDetailsDTO(String name, City city,
                             String code, Set<Shop> shops,
                             Set<Parking> parkings) {
        this.name = name;
        this.location = String.format("%s, %s", city.getName(), city.getCountry().getName());
        this.code = code;
        this.shops = toShopDTO(shops);
        this.parkings = toParkingDTO(parkings);
    }

    private Set<ShopDetailsDTO> toShopDTO(Set<Shop> shops) {
        return shops.stream()
                .map(shopMapper::toShopDetailsDTO)
                .collect(Collectors.toSet());
    }

    private Set<ParkingDetailsDTO> toParkingDTO(Set<Parking> parkings) {
        return parkings.stream()
                .map(parkingMapper::toParkingDTO)
                .collect(Collectors.toSet());
    }


}
