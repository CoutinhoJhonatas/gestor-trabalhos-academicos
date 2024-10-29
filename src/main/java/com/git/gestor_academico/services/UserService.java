package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.UserDTO;
import com.git.gestor_academico.models.Role;
import com.git.gestor_academico.models.User;
import com.git.gestor_academico.repositorys.RoleRepository;
import com.git.gestor_academico.repositorys.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public ResponseEntity<UserDTO> saveUser(UserDTO dto) {
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        List<Role> roles = roleRepository.findByRoleNameIn(dto.getRoles());
        if(dto.getRoles().size() != roles.size()) {
            //throw new RolesNotAvailableException(dto.getRoles());
        }
        entity.setRoles(Set.copyOf(roles));
        User savedUser = userRepository.save(entity);
        dto.setPassword("******");
        dto.setUserId(savedUser.getUserId());
        return ResponseEntity.ok(dto);
    }

    public boolean findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return true;
        }
        return false;
    }

}
