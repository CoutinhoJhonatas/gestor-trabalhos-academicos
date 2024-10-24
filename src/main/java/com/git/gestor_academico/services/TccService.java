package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.TccRequestDTO;
import com.git.gestor_academico.dtos.response.AlunoTccResponseDTO;
import com.git.gestor_academico.dtos.response.TccResponseDTO;
import com.git.gestor_academico.mappers.TccMapper;
import com.git.gestor_academico.models.Aluno;
import com.git.gestor_academico.models.Orientador;
import com.git.gestor_academico.models.Tcc;
import com.git.gestor_academico.repositorys.AlunoRepository;
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
    private final AlunoRepository alunoRepository;
    private final TccMapper tccMapper;
    private final AlunoService alunoService;

    private static final String TCC_NAO_ENCONTRADO = "TCC com o ID %d não encontrado";

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
        Orientador orientador = buscarOrientador(tccRequestDTO.getOrientadorMatricula());

        Tcc tcc = tccMapper.toDomain(tccRequestDTO);
        tcc.setOrientador(orientador);
        tcc = tccRepository.save(tcc);

        List<AlunoTccResponseDTO> alunoTccResponseDTOS = alunoService.vincularTccAluno(tcc, tccRequestDTO.getIntegrantesRA());
        TccResponseDTO tccResponseDTO = tccMapper.toResponseDTO(tcc);
        tccResponseDTO.setIntegrantes(alunoTccResponseDTOS);
        return tccResponseDTO;
    }

    @Transactional
    public TccResponseDTO atualizar(Long id, TccRequestDTO tccRequestDTO) {
        Tcc tcc = tccRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(TCC_NAO_ENCONTRADO, id)));

        tcc.setTitulo(tccRequestDTO.getTitulo());
        tcc.setLinkDocs(tccRequestDTO.getLinkDocs());
        tcc.setResumoProposta(tccRequestDTO.getResumoProposta());
        tcc.setOrientador(buscarOrientador(tccRequestDTO.getOrientadorMatricula()));
        tcc.setIntegrantes(getListAluno(tccRequestDTO.getIntegrantesRA()));

        tcc = tccRepository.save(tcc);
        return tccMapper.toResponseDTO(tcc);
    }

    //TODO colocar um atributo boolean "ativo" no tcc para não ter que excluir de vez o registro do tcc na base.
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

    private List<Aluno> getListAluno(List<Long> alunosRAs) {
        List<Aluno> alunos = new ArrayList<>();
        alunosRAs.forEach(ra -> alunos.add(alunoRepository.findById(ra)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno com o RA " + ra + " não encontrado"))));
        return alunos;
    }

    private Orientador buscarOrientador(Long matricula) {
        return orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException("Orientador com a matricula " + matricula + " não encontrado"));
    }

}
