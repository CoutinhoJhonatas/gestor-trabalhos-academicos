package com.git.gestor_academico.swagger;

import com.git.gestor_academico.dtos.exceptions.CustomError;
import com.git.gestor_academico.dtos.request.AlunoRequestDTO;
import com.git.gestor_academico.dtos.response.AlunoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Alunos")
public interface AlunoControllerSwagger {

    @Operation(summary = "Buscar todos os alunos")
    @ApiResponse(responseCode = "200", description = "Alunos retornados com sucesso",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AlunoResponseDTO.class)))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<List<AlunoResponseDTO>> getAll();

    @Operation(summary = "Buscar aluno pelo RA")
    @ApiResponse(responseCode = "200", description = "Aluno retornado com sucesso",
            content = {@Content(mediaType = "application/json",
                     schema = @Schema(implementation = AlunoResponseDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Aluno com o RA especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                     schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<AlunoResponseDTO> procurarPorRegistro(@Parameter(description = "RA do aluno") Long registro);

    @Operation(summary = "Salvar aluno")
    @ApiResponse(responseCode = "201", description = "Aluno salvo com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AlunoResponseDTO.class))
            })
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "O curso com o ID especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<AlunoResponseDTO> save(@Parameter(description = "Dados do aluno") AlunoRequestDTO aluno);

    @Operation(summary = "Atualizar aluno")
    @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AlunoResponseDTO.class))
            })
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "O aluno/curso com o RA/ID especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<AlunoResponseDTO> atualizar(@Parameter(description = "RA do aluno") Long registro,
                                               @Parameter(description = "Dados atualizados do aluno") AlunoRequestDTO aluno);

    @Operation(summary = "Deletar aluno")
    @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "O aluno com o RA especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<Void> deletar(@Parameter(description = "RA do aluno") Long registro);

}
