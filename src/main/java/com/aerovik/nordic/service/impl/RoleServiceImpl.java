package com.aerovik.nordic.service.impl;

import com.aerovik.nordic.enums.ERole;
import com.aerovik.nordic.exception.NotFoundException;
import com.aerovik.nordic.model.Role;
import com.aerovik.nordic.repository.RoleRepository;
import com.aerovik.nordic.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RoleServiceImpl implements RoleService {
    private static final String USER_ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getRoleIfExist(String[] roles) {
        checkRoleExistence(roles);
        log.info("Roles found!");
        return findByName(roles);
    }

    private void checkRoleExistence(String[] roles) {
        Arrays.stream(roles)
                .forEach(role -> {
                    if (!roleRepository.existsByName(ERole.valueOf(role))) {
                        log.error("Role {} not found!", role);
                        throw new NotFoundException(USER_ROLE_NOT_FOUND_ERROR);
                    }
                });
    }

    private Set<Role> findByName(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        Arrays.stream(roles)
                .forEach(role -> roleSet.add(roleRepository.findByName(ERole.valueOf(role))
                        .orElseThrow(() -> new NotFoundException(USER_ROLE_NOT_FOUND_ERROR))));

        return roleSet;
    }
}
