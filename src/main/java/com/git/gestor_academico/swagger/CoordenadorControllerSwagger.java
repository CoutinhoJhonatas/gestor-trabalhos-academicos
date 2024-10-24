package com.git.gestor_academico.swagger;

import com.git.gestor_academico.dtos.exceptions.CustomError;
import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.response.CoordenadorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Coordenadores")
public interface CoordenadorControllerSwagger {

    @Operation(summary = "Buscar todos os coordenadores")
    @ApiResponse(responseCode = "200", description = "Coordenadores retornados com sucesso",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CoordenadorResponseDTO.class)))
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
    ResponseEntity<List<CoordenadorResponseDTO>> buscarTodos();

    @Operation(summary = "Buscar coordenador pela matrícula")
    @ApiResponse(responseCode = "200", description = "Coordenador retornado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CoordenadorResponseDTO.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "Coordenador com a matrícula especificada não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<CoordenadorResponseDTO> procurarPorMatricula(@Parameter(description = "Matrícula do coordenador") Long matricula);

    @Operation(summary = "Salvar coordenador")
    @ApiResponse(responseCode = "201", description = "Coordenador salvo com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CoordenadorResponseDTO.class))
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
    ResponseEntity<CoordenadorResponseDTO> save(@Parameter(description = "Dados do coordenador") CoordenadorRequestDTO coordenador);

    @Operation(summary = "Atualizar coordenador")
    @ApiResponse(responseCode = "200", description = "Coordenador atualizado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CoordenadorResponseDTO.class))
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
    @ApiResponse(responseCode = "404", description = "O coordenador com a matrícula especificada não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<CoordenadorResponseDTO> atualizar(@Parameter(description = "Matrícula do coordenador") Long matricula,
                                                     @Parameter(description = "Dados do coordenador") CoordenadorRequestDTO coordenador);

    @Operation(summary = "Desativar coordenador")
    @ApiResponse(responseCode = "204", description = "Coordenador desativado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "O coordenador com a matrícula especificada não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<Void> desativar(@Parameter(description = "Matrícula do coordenador") Long matricula);
}
