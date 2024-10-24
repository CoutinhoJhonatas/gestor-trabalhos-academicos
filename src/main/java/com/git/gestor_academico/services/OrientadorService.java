package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.OrientadorDto;
import com.git.gestor_academico.mappers.OrientadorMapper;
import com.git.gestor_academico.models.Orientador;
import com.git.gestor_academico.models.enums.Disponibilidades;
import com.git.gestor_academico.models.enums.Titulacao;
import com.git.gestor_academico.repositorys.OrientadorRepository;
import com.git.gestor_academico.services.exceptions.DatabaseException;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrientadorService {

    private final OrientadorRepository orientadorRepository;
    private final OrientadorMapper orientadorMapper;

    private static final String ORIENTADOR_NAO_ENCONTRADO = "Orientador não encontrado";

    @Transactional(readOnly = true)
    public List<OrientadorDto> listarTodos() {
        List<Orientador> orientadores = orientadorRepository.findAll();
        List<OrientadorDto> orientadoresDtos = new ArrayList<>();
        orientadores.forEach(orientador -> orientadoresDtos.add(orientadorMapper.toDto(orientador)));
        return orientadoresDtos;
    }

    @Transactional(readOnly = true)
    public OrientadorDto buscarPorMatricula(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException(ORIENTADOR_NAO_ENCONTRADO));
        return orientadorMapper.toDto(orientador);
    }

    @Transactional
    public OrientadorDto salvar(OrientadorDto orientadorDto) {
        Orientador orientador = orientadorRepository.save(orientadorMapper.toDomain(orientadorDto));
        return orientadorMapper.toDto(orientador);
    }

    @Transactional
    public OrientadorDto atualizar(Long matricula, OrientadorDto orientadorDto) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException(ORIENTADOR_NAO_ENCONTRADO));

        orientador.setNome(orientadorDto.getNome());
        orientador.setTelefone(orientadorDto.getTelefone());
        orientador.setAreaConhecimento(orientadorDto.getAreaConhecimento());
        orientador.setTitulacao(Enum.valueOf(Titulacao.class, orientadorDto.getTitulacao()));
        orientador.setDisponibilidades(getListDisponibilidades(orientadorDto.getDisponibilidades()));

        orientadorRepository.save(orientador);
        return orientadorMapper.toDto(orientador);
    }

    //TODO colocar um atributo boolean "ativo" no orientador para não ter que excluir de vez o registro do orientador na base.
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletar(Long matricula) {
        if(!orientadorRepository.existsById(matricula)) {
            throw new ResourceNotFoundException(ORIENTADOR_NAO_ENCONTRADO);
        }
        try {
            orientadorRepository.deleteById(matricula);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private Set<Disponibilidades> getListDisponibilidades(Set<String> disponibilidades) {
        Set<Disponibilidades> list = new HashSet<>();
        disponibilidades.forEach(disponibilidade ->
                list.add(Enum.valueOf(Disponibilidades.class, disponibilidade)));
        return list;
    }
}
