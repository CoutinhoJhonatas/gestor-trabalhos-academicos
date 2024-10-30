package com.git.gestor_academico.services;

import com.git.gestor_academico.models.Role;
import com.git.gestor_academico.models.User;
import com.git.gestor_academico.repositorys.RoleRepository;
import com.git.gestor_academico.repositorys.UserRepository;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import com.git.gestor_academico.services.exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public boolean saveUser(String username, String pasword, String roleName) {
        if(findByUsername(username)) {
            throw new UserAlreadyExistException("Usuario já cadastrado na base de dados");
        }

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(pasword));
        user.setRoles(Set.of(role));
        user = userRepository.save(user);

        return user.getUserId() != null;
    }

    public boolean findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.isPresent();
    }

}
