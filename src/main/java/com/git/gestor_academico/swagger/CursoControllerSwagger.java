package com.git.gestor_academico.swagger;

import com.git.gestor_academico.dtos.exceptions.CustomError;
import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.request.CursoRequestDTO;
import com.git.gestor_academico.dtos.response.CoordenadorResponseDTO;
import com.git.gestor_academico.dtos.response.CursoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Cursos")
public interface CursoControllerSwagger {

    @Operation(summary = "Buscar todos os cursos")
    @ApiResponse(responseCode = "200", description = "Cursos retornados com sucesso",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CursoResponseDTO.class)))
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
    ResponseEntity<List<CursoResponseDTO>> buscarTodos();

    @Operation(summary = "Buscar curso pelo ID")
    @ApiResponse(responseCode = "200", description = "Cursor retornado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CursoResponseDTO.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "Curso com o ID especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<CursoResponseDTO> procurarPorId(@Parameter(description = "ID do curso") Long id);

    @Operation(summary = "Salvar curso")
    @ApiResponse(responseCode = "201", description = "Curso salvo com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CursoResponseDTO.class))
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
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<CursoResponseDTO> save(@Parameter(description = "Informações do curso") CursoRequestDTO curso);

    @Operation(summary = "Atualizar curso")
    @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CursoResponseDTO.class))
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
    ResponseEntity<CursoResponseDTO> atualizar(@Parameter(description = "ID do curso") Long id,
                                               @Parameter(description = "Informações do curso") CursoRequestDTO curso);

    @Operation(summary = "Desativar curso")
    @ApiResponse(responseCode = "204", description = "Curso desativado com sucesso")
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
    ResponseEntity<Void> desativar(@Parameter(description = "ID do curso") Long id);
}
