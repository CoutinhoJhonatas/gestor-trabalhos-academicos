package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.UserDTO;
import com.git.gestor_academico.models.Role;
import com.git.gestor_academico.models.User;
import com.git.gestor_academico.repositorys.AlunoRepository;
import com.git.gestor_academico.repositorys.CoordenadorRepository;
import com.git.gestor_academico.repositorys.OrientadorRepository;
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
    private final AlunoRepository alunoRepository;
    private final CoordenadorRepository coordenadorRepository;
    private final OrientadorRepository orientadorRepository;

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

    public UserDTO buscarDadosUsuario(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        String roleName = user.getRoles().stream().findFirst().get().getRoleName();

        UserDTO userDTO = new UserDTO();
        switch (roleName) {
            case "ROLE_ALUNO" -> {
                Long userId = Long.parseLong(username);
                userDTO.setUserId(userId);
                userDTO.setNome(alunoRepository.findById(userId).get().getNome());
                userDTO.setRole("ROLE_ALUNO");
            }
            case "ROLE_ORIENTADOR" -> {
                Long userId = Long.parseLong(username);
                userDTO.setUserId(userId);
                userDTO.setNome(orientadorRepository.findById(userId).get().getNome());
                userDTO.setRole("ROLE_ORIENTADOR");
            }
            case "ROLE_COORDENADOR" -> {
                Long userId = Long.parseLong(username);
                userDTO.setUserId(userId);
                userDTO.setNome(coordenadorRepository.findById(userId).get().getNome());
                userDTO.setRole("ROLE_COORDENADOR");
            }
            default -> {
                userDTO.setUserId(7L);
                userDTO.setNome("ADMIN");
                userDTO.setRole("ROLE_ADMIN");
            }
        }

        return userDTO;
    }

}
