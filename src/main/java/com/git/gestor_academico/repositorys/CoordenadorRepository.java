package com.git.gestor_academico.repositorys;

import com.git.gestor_academico.models.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

}
