package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.OrientadorDto;
import com.git.gestor_academico.mappers.OrientadorMapper;
import com.git.gestor_academico.models.Orientador;
import com.git.gestor_academico.models.enums.Disponibilidades;
import com.git.gestor_academico.models.enums.Titulacao;
import com.git.gestor_academico.repositorys.OrientadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrientadorService {

    private final OrientadorRepository orientadorRepository;

    private final OrientadorMapper orientadorMapper;

    public List<OrientadorDto> listarTodos() {
        List<Orientador> orientadores = orientadorRepository.findAll();
        List<OrientadorDto> orientadoresDtos = new ArrayList<>();
        orientadores.forEach(orientador -> orientadoresDtos.add(orientadorMapper.toDto(orientador)));
        return orientadoresDtos;
    }

    public OrientadorDto buscarPorMatricula(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResolutionException("Orientador não encontrado"));
        return orientadorMapper.toDto(orientador);
    }

    public OrientadorDto salvar(OrientadorDto orientadorDto) {
        Orientador orientador = orientadorRepository.save(orientadorMapper.toDomain(orientadorDto));
        return orientadorMapper.toDto(orientador);
    }

    public OrientadorDto atualizar(Long matricula, OrientadorDto orientadorDto) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResolutionException("Orientador não encontrado") );

        if(orientadorDto.getNome() != null) {
            orientador.setNome(orientadorDto.getNome());
        }

        if(orientadorDto.getTelefone() != null) {
            orientador.setTelefone(orientadorDto.getTelefone());
        }

        if(orientadorDto.getAreaConhecimento() != null) {
            orientador.setAreaConhecimento(orientadorDto.getAreaConhecimento());
        }

        if(orientadorDto.getTitulacao() != null) {
            orientador.setTitulacao(Enum.valueOf(Titulacao.class, orientadorDto.getTitulacao()));
        }

        if(orientadorDto.getDisponibilidades() != null) {
            orientador.setDisponibilidades(getListDisponibilidades(orientadorDto.getDisponibilidades()));
        }

        orientadorRepository.save(orientador);

        return orientadorMapper.toDto(orientador);
    }

    public void deletar(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResolutionException("Orientador não encontrado"));
        orientadorRepository.deleteById(orientador.getMatricula());
    }

    private Set<Disponibilidades> getListDisponibilidades(Set<String> disponibilidades) {
        Set<Disponibilidades> list = new HashSet<>();
        disponibilidades.forEach(disponibilidade ->
                list.add(Enum.valueOf(Disponibilidades.class, disponibilidade)));
        return list;
    }
}
