package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.request.AlunoRequestDTO;
import com.git.gestor_academico.dtos.response.AlunoResponseDTO;
import com.git.gestor_academico.services.AlunoService;
import com.git.gestor_academico.swagger.AlunoControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class AlunoController implements AlunoControllerSwagger {

    private final AlunoService alunoService;

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> getAll() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping("/{registro}")
    public ResponseEntity<AlunoResponseDTO> procurarPorRegistro(@PathVariable Long registro) {
        return new ResponseEntity<>(alunoService.buscarPorRegistro(registro), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> save(@Valid @RequestBody AlunoRequestDTO aluno) {
        return new ResponseEntity<>(alunoService.salvar(aluno), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PutMapping("/{registro}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long registro,
                                                      @Valid @RequestBody AlunoRequestDTO aluno) {
        return new ResponseEntity<>(alunoService.atualizar(registro, aluno), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @DeleteMapping("/{registro}")
    public ResponseEntity<Void> deletar(@PathVariable Long registro) {
        alunoService.deletar(registro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
