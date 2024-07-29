package com.aerovik.nordic.service.impl;

import com.aerovik.nordic.enums.ERole;
import com.aerovik.nordic.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String username;
    @JsonIgnore
    private final String email;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomerPrincipal(Long id,
                               String firstName,
                               String lastName,
                               String username,
                               String email,
                               String password,
                               Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = (authorities != null) ? new ArrayList<>(authorities) : null;

    }

    public static CustomerPrincipal build(Customer customer) {
        List<SimpleGrantedAuthority> authorities =
                customer.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .toList();

        return new CustomerPrincipal(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : new ArrayList<>(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public static boolean isCurrentAccountAdmin(CustomerPrincipal currentAccount) {
        return currentAccount
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString()));
    }
}
