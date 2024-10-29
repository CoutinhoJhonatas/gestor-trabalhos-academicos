package com.git.gestor_academico.repositorys;

import com.git.gestor_academico.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByRoleNameIn(List<String> roles);

}
