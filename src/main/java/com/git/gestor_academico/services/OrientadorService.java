package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.response.OrientadorResponseDTO;
import com.git.gestor_academico.dtos.request.OrientadorRequestDTO;
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
    private final UserService userService;

    private static final String ORIENTADOR_NAO_ENCONTRADO = "Orientador não encontrado";

    @Transactional(readOnly = true)
    public List<OrientadorResponseDTO> listarTodos() {
        List<Orientador> orientadores = orientadorRepository.findAll();
        List<OrientadorResponseDTO> orientadoresDtos = new ArrayList<>();
        orientadores.forEach(orientador -> orientadoresDtos.add(orientadorMapper.toDto(orientador)));
        return orientadoresDtos;
    }

    @Transactional(readOnly = true)
    public OrientadorResponseDTO buscarPorMatricula(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException(ORIENTADOR_NAO_ENCONTRADO));
        return orientadorMapper.toDto(orientador);
    }

    @Transactional
    public OrientadorResponseDTO salvar(OrientadorRequestDTO orientadorRequestDTO) {
        if(!userService.saveUser(
                orientadorRequestDTO.getMatricula().toString(),
                orientadorRequestDTO.getSenha(),
                "ROLE_ORIENTADOR")) {
            throw new RuntimeException("Erro ao salvar usuário");
        }

        Orientador orientador = orientadorRepository.save(orientadorMapper.toDomain(orientadorRequestDTO));
        return orientadorMapper.toDto(orientador);
    }

    @Transactional
    public OrientadorResponseDTO atualizar(Long matricula, OrientadorRequestDTO orientadorResponseDTO) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException(ORIENTADOR_NAO_ENCONTRADO));

        orientador.setNome(orientadorResponseDTO.getNome());
        orientador.setTelefone(orientadorResponseDTO.getTelefone());
        orientador.setAreaConhecimento(orientadorResponseDTO.getAreaConhecimento());
        orientador.setTitulacao(Enum.valueOf(Titulacao.class, orientadorResponseDTO.getTitulacao()));
        orientador.setDisponibilidades(getListDisponibilidades(orientadorResponseDTO.getDisponibilidades()));

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
