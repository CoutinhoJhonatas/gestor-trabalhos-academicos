package com.git.gestor_academico.repositorys;

import com.git.gestor_academico.models.Orientacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientacaoRepository extends JpaRepository<Orientacao, Long> {
}
