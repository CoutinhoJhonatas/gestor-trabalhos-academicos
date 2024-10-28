package com.git.gestor_academico.projections;

import java.time.LocalDateTime;

public interface CheckpointProjection {

    Long getId();
    Boolean getEntregaValidada();
    String getObservacoes();
    Boolean getNecessitaRevisar();
    LocalDateTime getData();
}
