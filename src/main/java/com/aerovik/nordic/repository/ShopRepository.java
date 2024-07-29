package com.aerovik.nordic.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aerovik.nordic.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{
}
