package com.git.gestor_academico.repositorys;

import com.git.gestor_academico.models.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientadorRepository extends JpaRepository<Orientador, Long> {
}
