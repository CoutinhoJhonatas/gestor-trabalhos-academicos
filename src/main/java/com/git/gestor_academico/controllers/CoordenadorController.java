package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.response.CoordenadorResponseDTO;
import com.git.gestor_academico.services.CoordenadorService;
import com.git.gestor_academico.swagger.CoordenadorControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/coordenadores")
@AllArgsConstructor
public class CoordenadorController implements CoordenadorControllerSwagger {

    private final CoordenadorService coordenadorService;

    @GetMapping
    public ResponseEntity<List<CoordenadorResponseDTO>> buscarTodos() {
        return new ResponseEntity<>(coordenadorService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<CoordenadorResponseDTO> procurarPorMatricula(@PathVariable Long matricula) {
        return new ResponseEntity<>(coordenadorService.buscarPorMatricula(matricula), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoordenadorResponseDTO> save(@Valid @RequestBody CoordenadorRequestDTO coordenador) {
        return new ResponseEntity<>(coordenadorService.salvar(coordenador), HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<CoordenadorResponseDTO> atualizar(@PathVariable Long matricula,
                                                   @Valid @RequestBody CoordenadorRequestDTO coordenador) {
        return new ResponseEntity<>(coordenadorService.atualizar(matricula, coordenador), HttpStatus.OK);
    }

    @PutMapping("/desativar-cadastro/{matricula}")
    public ResponseEntity<Void> desativar(@PathVariable Long matricula) {
        coordenadorService.desativarCadastro(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
