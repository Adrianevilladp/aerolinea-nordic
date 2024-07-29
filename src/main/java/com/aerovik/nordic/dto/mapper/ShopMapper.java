package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.ShopDetailsDTO;
import com.aerovik.nordic.model.Shop;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {
    public ShopDetailsDTO toShopDetailsDTO(Shop shop) {
        return new ShopDetailsDTO(
                shop.getName(),
                shop.getAirportZone().name()
        );
    }

}
