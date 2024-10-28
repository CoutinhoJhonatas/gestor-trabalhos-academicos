package com.git.gestor_academico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_checkpoint")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Checkpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean entregaValidada;
    private String observacoes;
    private Boolean necessitaRevisar;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "orientacao_id")
    private Orientacao orientacao;

}
