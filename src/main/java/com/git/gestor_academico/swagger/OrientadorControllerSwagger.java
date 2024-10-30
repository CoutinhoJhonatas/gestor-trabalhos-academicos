package com.git.gestor_academico.swagger;

import com.git.gestor_academico.dtos.request.OrientadorRequestDTO;
import com.git.gestor_academico.dtos.response.OrientadorResponseDTO;
import com.git.gestor_academico.dtos.exceptions.CustomError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Orientadores")
public interface OrientadorControllerSwagger {

    @Operation(summary = "Buscar todos os orientadores")
    @ApiResponse(responseCode = "200", description = "Orientadores retornados com sucesso",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = OrientadorResponseDTO.class)))
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
    ResponseEntity<List<OrientadorResponseDTO>> getAll();

    @Operation(summary = "Buscar orientador pela matrícula")
    @ApiResponse(responseCode = "200", description = "Orientador retornado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrientadorResponseDTO.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "Orientador com a matrícula especificada não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<OrientadorResponseDTO> procurarPorMatricula(@Parameter(description = "Matrícula do orientador") Long matricula);

    @Operation(summary = "Salvar orientador")
    @ApiResponse(responseCode = "201", description = "Orientador salvo com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrientadorResponseDTO.class))
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
    ResponseEntity<OrientadorResponseDTO> save(@Parameter(description = "Dados do orientador") OrientadorRequestDTO orientador);

    @Operation(summary = "Atualizar orientador")
    @ApiResponse(responseCode = "200", description = "Orientador atualizado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrientadorResponseDTO.class))
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
    @ApiResponse(responseCode = "404", description = "O orientador com a matrícula especificada não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<OrientadorResponseDTO> atualizar(@Parameter(description = "Matrícula do orientador") Long matricula,
                                                    @Parameter(description = "Dados atualizados do orientador") OrientadorRequestDTO orientador);

    @Operation(summary = "Deletar orientador")
    @ApiResponse(responseCode = "204", description = "Orientador deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "O orientador com a matrícula especificada não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<Void> deletar(@Parameter(description = "Matrícula do orientador") Long matricula);

}
