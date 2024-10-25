package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.CursoRequestDTO;
import com.git.gestor_academico.dtos.response.CursoResponseDTO;
import com.git.gestor_academico.mappers.CursoMapper;
import com.git.gestor_academico.models.Curso;
import com.git.gestor_academico.repositorys.CursoRepository;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    private static final String CURSO_NAO_ENCONTRADO = "Curso com o ID %d não foi encontrado";

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> listarTodos() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoResponseDTO> cursoResponseDtos = new ArrayList<>();
        cursos.forEach(curso -> cursoResponseDtos.add(cursoMapper.toResponseDTO(curso)));
        return cursoResponseDtos;
    }

    @Transactional(readOnly = true)
    public CursoResponseDTO buscarPorMatricula(Long id) {
        Curso curso = findById(id);
        return cursoMapper.toResponseDTO(curso);
    }

    @Transactional
    public CursoResponseDTO salvar(CursoRequestDTO cursoRequestDTO) {
        Curso curso = cursoMapper.toDomain(cursoRequestDTO);
        curso.setAtivo(true);
        curso = cursoRepository.save(curso);
        return cursoMapper.toResponseDTO(curso);
    }

    @Transactional
    public CursoResponseDTO atualizar(Long id, CursoRequestDTO cursoRequestDTO) {
        Curso curso = findById(id);
        curso.setNome(cursoRequestDTO.getNome());
        curso = cursoRepository.save(curso);
        return cursoMapper.toResponseDTO(curso);
    }

    @Transactional
    public void desativarCurso(Long id) {
        Curso curso = findById(id);
        curso.setAtivo(false);
        cursoRepository.save(curso);
    }

    private Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CURSO_NAO_ENCONTRADO, id)));
    }

}
