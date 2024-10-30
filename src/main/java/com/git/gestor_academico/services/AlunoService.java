package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.AlunoRequestDTO;
import com.git.gestor_academico.dtos.response.AlunoResponseDTO;
import com.git.gestor_academico.dtos.response.AlunoTccResponseDTO;
import com.git.gestor_academico.mappers.AlunoMapper;
import com.git.gestor_academico.models.Aluno;
import com.git.gestor_academico.models.Curso;
import com.git.gestor_academico.models.Tcc;
import com.git.gestor_academico.repositorys.AlunoRepository;
import com.git.gestor_academico.repositorys.CursoRepository;
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
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final AlunoMapper alunoMapper;
    private final UserService userService;

    private static final String ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> listarTodos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoResponseDTO> alunoResponseDTOS = new ArrayList<>();
        alunos.forEach(aluno -> alunoResponseDTOS.add(alunoMapper.toResponseDto(aluno)));
        return alunoResponseDTOS;
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarPorRegistro(Long registro) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new ResourceNotFoundException(ALUNO_NAO_ENCONTRADO));
        return alunoMapper.toResponseDto(aluno);
    }

    @Transactional
    public AlunoResponseDTO salvar(AlunoRequestDTO alunoRequestDTO) {
        if(!userService.saveUser(alunoRequestDTO.getRegistroAluno().toString(), alunoRequestDTO.getSenha(), "ROLE_ALUNO")) {
            throw new RuntimeException("Erro ao salvar usuário");
        }

        Curso curso = cursoRepository.findById(alunoRequestDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso com o id " + alunoRequestDTO.getCursoId() + " não encontrado"));
        Aluno aluno = alunoMapper.toDomain(alunoRequestDTO);
        aluno.setCurso(curso);
        aluno = alunoRepository.save(aluno);
        return alunoMapper.toResponseDto(aluno);
    }

    @Transactional
    public AlunoResponseDTO atualizar(Long registro, AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new ResourceNotFoundException(ALUNO_NAO_ENCONTRADO));

        aluno.setNome(alunoRequestDTO.getNome());
        aluno.setTurma(alunoRequestDTO.getTurma());
        aluno.setTelefone(alunoRequestDTO.getTelefone());

        Curso curso = cursoRepository.findById(alunoRequestDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso com o id " + alunoRequestDTO.getCursoId() + " não encontrado"));
        aluno.setCurso(curso);

        aluno = alunoRepository.save(aluno);
        return alunoMapper.toResponseDto(aluno);
    }

    //TODO colocar um atributo boolean "ativo" no aluno para não ter que excluir de vez o registro do aluno na base.
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletar(Long registro) {
        if(!alunoRepository.existsById(registro)) {
            throw new ResourceNotFoundException(ALUNO_NAO_ENCONTRADO);
        }
        try {
            alunoRepository.deleteById(registro);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional
    public List<AlunoTccResponseDTO> vincularTccAluno(Tcc tcc, List<Long> alunosRAs) {
        List<AlunoTccResponseDTO> alunoTccResponseDTOS = new ArrayList<>();

        alunosRAs.forEach(ra -> {
            Aluno aluno = alunoRepository.findById(ra)
                    .orElseThrow(() -> new ResourceNotFoundException(ALUNO_NAO_ENCONTRADO));
            aluno.setTcc(tcc);
            aluno = alunoRepository.save(aluno);
            alunoTccResponseDTOS.add(alunoMapper.toTccResponseDto(aluno));
        });

        return alunoTccResponseDTOS;
    }
}
