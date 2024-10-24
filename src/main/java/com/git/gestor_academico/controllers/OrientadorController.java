package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.OrientadorDto;
import com.git.gestor_academico.services.OrientadorService;
import com.git.gestor_academico.swagger.OrientadorControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/orientadores")
@AllArgsConstructor
public class OrientadorController implements OrientadorControllerSwagger {

    private final OrientadorService orientadorService;

    @GetMapping
    public ResponseEntity<List<OrientadorDto>> getAll() {
        return ResponseEntity.ok(orientadorService.listarTodos());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<OrientadorDto> procurarPorMatricula(@PathVariable Long matricula) {
        return new ResponseEntity<>(orientadorService.buscarPorMatricula(matricula), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrientadorDto> save(@Valid @RequestBody OrientadorDto orientador) {
        return new ResponseEntity<>(orientadorService.salvar(orientador), HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<OrientadorDto> atualizar(@PathVariable Long matricula,
                                                   @Valid @RequestBody OrientadorDto orientador) {
        return new ResponseEntity<>(orientadorService.atualizar(matricula, orientador), HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable Long matricula) {
        orientadorService.deletar(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
