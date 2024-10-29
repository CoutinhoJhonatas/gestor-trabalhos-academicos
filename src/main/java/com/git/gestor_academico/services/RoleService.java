package com.git.gestor_academico.services;

import com.git.gestor_academico.models.Role;
import com.git.gestor_academico.repositorys.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void loadRoles() {
        List<Role> roles = new ArrayList<>();
        Role roleUser = new Role();
        roleUser.setRoleName("ROLE_USER");
        roles.add(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ROLE_ADMIN");
        roles.add(roleAdmin);
        roleRepository.saveAll(roles);
    }

}
