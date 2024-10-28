package com.git.gestor_academico.repositorys;

import com.git.gestor_academico.models.Checkpoint;
import com.git.gestor_academico.projections.CheckpointProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {

    @Query(nativeQuery = true,
            value = "SELECT c.id, c.entrega_validada, c.observacoes, c.necessita_revisar, c.data " +
                    "FROM tb_checkpoint c " +
                    "WHERE c.orientacao_id = :orientacaoId " +
                    "ORDER BY c.id desc")
    List<CheckpointProjection> buscarCheckpointsPorIdOrientacao(Long orientacaoId);

}
