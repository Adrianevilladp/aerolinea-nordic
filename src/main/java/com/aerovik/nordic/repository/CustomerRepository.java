package com.aerovik.nordic.repository;

import com.aerovik.nordic.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    //@Query("SELECT c FROM Customer c WHERE c.username = ?1 OR c.email = ?2")
    Optional<Customer> findByUsernameOrEmail(String username, String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = ?1")
    boolean existsByEmail(String s);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.username = ?1")
    boolean existsByUsername(String s);

    @Query("SELECT c FROM Customer c WHERE c.username = ?1")
    Optional<Customer> findByUsername(String username);
}
