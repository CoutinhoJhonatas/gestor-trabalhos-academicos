package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.OrientacaoRequestDTO;
import com.git.gestor_academico.dtos.response.OrientacaoResponseDTO;
import com.git.gestor_academico.mappers.OrientacaoMapper;
import com.git.gestor_academico.models.Orientacao;
import com.git.gestor_academico.models.Orientador;
import com.git.gestor_academico.models.Tcc;
import com.git.gestor_academico.models.enums.OrientacaoStatus;
import com.git.gestor_academico.repositorys.OrientacaoRepository;
import com.git.gestor_academico.repositorys.OrientadorRepository;
import com.git.gestor_academico.repositorys.TccRepository;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrientacaoService {

    private final OrientacaoRepository orientacaoRepository;
    private final TccRepository tccRepository;
    private final OrientadorRepository orientadorRepository;
    private final OrientacaoMapper orientacaoMapper;

    private static final String ORIENTACAO_NAO_ENCONTRADO = "Coordenador com o ID %d não foi encontrado";

    @Transactional(readOnly = true)
    public OrientacaoResponseDTO buscarPorMatricula(Long id) {
        Orientacao orientacao = findById(id);
        return orientacaoMapper.toResponseDTO(orientacao);
    }

    @Transactional
    public OrientacaoResponseDTO salvar(OrientacaoRequestDTO orientacaoRequestDTO) {
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
