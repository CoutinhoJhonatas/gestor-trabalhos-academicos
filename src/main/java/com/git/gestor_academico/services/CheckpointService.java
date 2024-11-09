package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.CheckpointRequestDTO;
import com.git.gestor_academico.dtos.response.CheckpointResponseDTO;
import com.git.gestor_academico.mappers.CheckpointMapper;
import com.git.gestor_academico.models.Aluno;
import com.git.gestor_academico.models.Checkpoint;
import com.git.gestor_academico.models.Orientacao;
import com.git.gestor_academico.repositorys.CheckpointRepository;
import com.git.gestor_academico.repositorys.OrientacaoRepository;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckpointService {

    private final CheckpointRepository checkpointRepository;
    private final OrientacaoRepository orientacaoRepository;
    private final CheckpointMapper checkpointMapper;
    private final EmailService emailService;

    @Transactional
    public CheckpointResponseDTO salvar(CheckpointRequestDTO checkpointRequestDTO) {
        Orientacao orientacao = orientacaoRepository.findById(checkpointRequestDTO.getOrientacaoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Orientação com o ID " + checkpointRequestDTO.getOrientacaoId() + " não foi encontrado."));

        Checkpoint checkpoint = checkpointMapper.toDomain(checkpointRequestDTO);
        checkpoint.setData(LocalDateTime.now());
        checkpoint.setOrientacao(orientacao);

        checkpoint = checkpointRepository.save(checkpoint);
        CheckpointResponseDTO responseDTO = checkpointMapper.toResponseDTO(checkpoint);
        responseDTO.setData(checkpoint.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        if(Boolean.TRUE.equals(checkpointRequestDTO.getEntregaValidada())) {
            enviarEmailAlunosOrientacaoAtualizada(orientacao.getTcc().getIntegrantes());
        }

        if(Boolean.FALSE.equals(checkpointRequestDTO.getEntregaValidada())){
            emailService.sendEmail(
                    orientacao.getOrientador().getEmail(),
                    "Validação de TCC",
                    "O trabalho com o título: " + orientacao.getTcc().getTitulo().toUpperCase() + " foi enviado para validação");
        }

        return responseDTO;
    }

    private void enviarEmailAlunosOrientacaoAtualizada(List<Aluno> alunos) {
        alunos.forEach(aluno -> emailService.sendEmail(
                aluno.getEmail(),
                "Validação da entrega do TCC",
                "Houve uma validação da entrega."));
    }

}
