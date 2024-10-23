package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.TccRequestDTO;
import com.git.gestor_academico.dtos.response.AlunoResponseDTO;
import com.git.gestor_academico.dtos.response.TccResponseDTO;
import com.git.gestor_academico.dtos.request.AlunoRequestDTO;
import com.git.gestor_academico.mappers.AlunoMapper;
import com.git.gestor_academico.mappers.OrientadorMapper;
import com.git.gestor_academico.mappers.TccMapper;
import com.git.gestor_academico.models.Aluno;
import com.git.gestor_academico.models.Orientador;
import com.git.gestor_academico.models.Tcc;
import com.git.gestor_academico.repositorys.OrientadorRepository;
import com.git.gestor_academico.repositorys.TccRepository;
import com.git.gestor_academico.services.exceptions.DatabaseException;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TccService {

    private final TccRepository tccRepository;
    private final OrientadorRepository orientadorRepository;
    private final TccMapper tccMapper;
    private final AlunoMapper alunoMapper;
    private final OrientadorMapper orientadorMapper;
    private final AlunoService alunoService;

    private static final String TCC_NAO_ENCONTRADO = "TCC com o id %d não encontrado";

    @Transactional(readOnly = true)
    public List<TccResponseDTO> listarTodos() {
        List<Tcc> tccs = tccRepository.findAll();
        List<TccResponseDTO> tccResponseDTOS = new ArrayList<>();
        tccs.forEach(tcc -> tccResponseDTOS.add(tccMapper.toResponseDTO(tcc)));
        return tccResponseDTOS;
    }

    @Transactional(readOnly = true)
    public TccResponseDTO buscarPorId(Long id) {
        Tcc tcc = tccRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(TCC_NAO_ENCONTRADO, id)));
        return tccMapper.toResponseDTO(tcc);
    }

    @Transactional
    public TccResponseDTO salvar(TccRequestDTO tccRequestDTO) {
        Orientador orientador = orientadorRepository.findById(tccRequestDTO.getOrientadorMatricula())
                .orElseThrow(() -> new ResourceNotFoundException("Orientador com a matricula " +
                        tccRequestDTO.getOrientadorMatricula() + " não encontrado"));

        Tcc tcc = tccMapper.toDomain(tccRequestDTO);
        tcc.setOrientador(orientador);
        tcc = tccRepository.save(tcc);

        List<AlunoResponseDTO> alunoResponseDTOS = alunoService.vincularTccAluno(tcc, tccRequestDTO.getIntegrantesRA());
        TccResponseDTO tccResponseDTO = tccMapper.toResponseDTO(tcc);
        tccResponseDTO.setIntegrantes(alunoResponseDTOS);
        return tccResponseDTO;
    }

    /*@Transactional
    public TccResponseDTO atualizar(Long id, TccRequestDTO tccResponseDTO) {
        Tcc tcc = tccRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(TCC_NAO_ENCONTRADO, id)));

        if(tccResponseDTO.getTitulo() != null) {
            tcc.setTitulo(tccResponseDTO.getTitulo());
        }

        if(tccResponseDTO.getIntegrantes() != null) {
            tcc.setIntegrantes(getListAluno(tccResponseDTO.getIntegrantes()));
        }

        if(tccResponseDTO.getOrientador() != null) {
            tcc.setOrientador(orientadorMapper.toDomain(tccResponseDTO.getOrientador()));
        }

        tccRepository.save(tcc);

        return tccMapper.toResponseDTO(tcc);
    }*/

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletar(Long id) {
        if(!tccRepository.existsById(id)) {
            throw new ResourceNotFoundException(TCC_NAO_ENCONTRADO);
        }
        try {
            tccRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private List<Aluno> getListAluno(List<AlunoRequestDTO> alunosDtos) {
        List<Aluno> alunos = new ArrayList<>();
        alunosDtos.forEach(aluno -> alunos.add(alunoMapper.toDomain(aluno)));
        return alunos;
    }

}
