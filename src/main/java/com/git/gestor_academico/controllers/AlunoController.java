package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.AlunoDto;
import com.git.gestor_academico.services.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("v1/alunos")
@AllArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDto>> getAll() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{registro}")
    public ResponseEntity<AlunoDto> procurarPorRegistro(@PathVariable Long registro) {
        return new ResponseEntity<>(alunoService.buscarPorRegistro(registro), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> save(@RequestBody AlunoDto aluno) {
        return new ResponseEntity<>(alunoService.salvar(aluno), HttpStatus.CREATED);
    }

    @PutMapping("/{registro}")
    public ResponseEntity<AlunoDto> atualizar(@PathVariable Long registro,
                                                @RequestBody AlunoDto aluno) {

        return new ResponseEntity<>(alunoService.atualizar(registro, aluno), HttpStatus.OK);
    }

    @DeleteMapping("/{registro}")
    public ResponseEntity<Void> deletar(@PathVariable Long registro) {
        alunoService.deletar(registro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
