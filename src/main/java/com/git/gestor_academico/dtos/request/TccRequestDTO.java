package com.git.gestor_academico.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TccRequestDTO {

    @NotBlank(message = "Campo requerido")
    private String titulo;

    private String linkDocs;

    @NotBlank(message = "Campo requerido")
    private String resumoProposta;

    @NotNull(message = "Campo requerido")
    private Long orientadorMatricula;

    @NotNull(message = "Campo requerido")
    private List<Long> integrantesRA;

}
