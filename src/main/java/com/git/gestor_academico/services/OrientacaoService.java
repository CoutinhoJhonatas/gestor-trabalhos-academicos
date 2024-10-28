package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.OrientacaoRequestDTO;
import com.git.gestor_academico.dtos.response.CheckpointResponseDTO;
import com.git.gestor_academico.dtos.response.OrientacaoResponseDTO;
import com.git.gestor_academico.mappers.OrientacaoMapper;
import com.git.gestor_academico.models.Checkpoint;
import com.git.gestor_academico.models.Orientacao;
import com.git.gestor_academico.models.Orientador;
import com.git.gestor_academico.models.Tcc;
import com.git.gestor_academico.models.enums.OrientacaoStatus;
import com.git.gestor_academico.projections.CheckpointProjection;
import com.git.gestor_academico.repositorys.CheckpointRepository;
import com.git.gestor_academico.repositorys.OrientacaoRepository;
import com.git.gestor_academico.repositorys.OrientadorRepository;
import com.git.gestor_academico.repositorys.TccRepository;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrientacaoService {

    private final OrientacaoRepository orientacaoRepository;
    private final TccRepository tccRepository;
    private final OrientadorRepository orientadorRepository;
    private final CheckpointRepository checkpointRepository;
    private final OrientacaoMapper orientacaoMapper;

    private static final String ORIENTACAO_NAO_ENCONTRADO = "Coordenador com o ID %d não foi encontrado";

    @Transactional(readOnly = true)
    public OrientacaoResponseDTO buscarPorMatricula(Long id) {
        Orientacao orientacao = findById(id);

        OrientacaoResponseDTO responseDTO =  orientacaoMapper.toResponseDTO(orientacao);
        List<CheckpointProjection> checkpointsProjections = checkpointRepository.buscarCheckpointsPorIdOrientacao(id);
        List<CheckpointResponseDTO> checkpoints = new ArrayList<>();

        checkpointsProjections.forEach(c -> {
            CheckpointResponseDTO checkpointResponseDTO = new CheckpointResponseDTO();
            checkpointResponseDTO.setId(c.getId());
            checkpointResponseDTO.setEntregaValidada(c.getEntregaValidada());
            checkpointResponseDTO.setObservacoes(c.getObservacoes());
            checkpointResponseDTO.setNecessitaRevisar(c.getNecessitaRevisar());
            checkpointResponseDTO.setData(c.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            checkpoints.add(checkpointResponseDTO);
        });

        responseDTO.setCheckpoints(checkpoints);

        return responseDTO;
    }

    @Transactional
    public OrientacaoResponseDTO salvar(OrientacaoRequestDTO orientacaoRequestDTO) {
        //TODO fazer validacao para caso ja exista uma orientacao com o tccId enviado no dto (tccId = primaryKey da orientacao);
        Orientador orientador = buscarOrientador(orientacaoRequestDTO.getOrientadorMatricula());
        Tcc tcc = buscarTcc(orientacaoRequestDTO.getTccId());

        Orientacao orientacao = orientacaoMapper.toDomain(orientacaoRequestDTO);
        orientacao.setOrientador(orientador);
        orientacao.setTcc(tcc);

        orientacao = orientacaoRepository.save(orientacao);
        return orientacaoMapper.toResponseDTO(orientacao);
    }

    @Transactional
    public OrientacaoResponseDTO atualizar(Long id, OrientacaoRequestDTO orientacaoRequestDTO) {
        Orientacao orientacao = orientacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORIENTACAO_NAO_ENCONTRADO, id)));

        orientacao.setStatus(Enum.valueOf(OrientacaoStatus.class, orientacaoRequestDTO.getStatus()));
        orientacao.setMotivoRecusado(orientacaoRequestDTO.getMotivoRecusado());
        orientacao.setOrientador(buscarOrientador(orientacaoRequestDTO.getOrientadorMatricula()));
        orientacao.setTcc(buscarTcc(orientacaoRequestDTO.getTccId()));

        orientacao = orientacaoRepository.save(orientacao);
        return orientacaoMapper.toResponseDTO(orientacao);
    }

    private Orientacao findById(Long id) {
        return orientacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORIENTACAO_NAO_ENCONTRADO, id)));
    }

    private Orientador buscarOrientador(Long matricula) {
        return orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException("Orientador com a matricula " + matricula + " não encontrado"));
    }

    private Tcc buscarTcc(Long id) {
        return tccRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TCC com o ID " + id + " não encontrado"));
    }

}
