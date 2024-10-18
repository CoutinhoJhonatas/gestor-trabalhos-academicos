package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.AlunoDto;
import com.git.gestor_academico.mappers.AlunoMapper;
import com.git.gestor_academico.models.Aluno;
import com.git.gestor_academico.repositorys.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final AlunoMapper alunoMapper;

    public List<AlunoDto> listarTodos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoDto> alunoDtos = new ArrayList<>();
        alunos.forEach(aluno -> alunoDtos.add(alunoMapper.toDto(aluno)));
        return alunoDtos;
    }

    public AlunoDto buscarPorRegistro(Long registro) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new ResolutionException("Aluno não encontrado"));
        return alunoMapper.toDto(aluno);
    }

    public AlunoDto salvar(AlunoDto alunoDto) {
        Aluno aluno = alunoRepository.save(alunoMapper.toDomain(alunoDto));
        return alunoMapper.toDto(aluno);
    }

    public AlunoDto atualizar(Long registro, AlunoDto alunoDto) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new ResolutionException("Aluno não encontrado") );

        if(alunoDto.getNome() != null) {
            aluno.setNome(alunoDto.getNome());
        }

        if(alunoDto.getTurma() != null) {
            aluno.setTurma(alunoDto.getTurma());
        }

        if(alunoDto.getCurso() != null) {
            aluno.setCurso(alunoDto.getCurso());
        }

        if(alunoDto.getTelefone() != null) {
            aluno.setTelefone(alunoDto.getTelefone());
        }

        alunoRepository.save(aluno);

        return alunoMapper.toDto(aluno);
    }

    public void deletar(Long registro) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new ResolutionException("Aluno não encontrado"));
        alunoRepository.deleteById(aluno.getRegistroAluno());
    }
}
