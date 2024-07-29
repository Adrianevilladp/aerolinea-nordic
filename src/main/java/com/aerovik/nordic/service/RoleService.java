package com.aerovik.nordic.service;

import com.aerovik.nordic.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Set<Role> getRoleIfExist(String[] roles);

}
