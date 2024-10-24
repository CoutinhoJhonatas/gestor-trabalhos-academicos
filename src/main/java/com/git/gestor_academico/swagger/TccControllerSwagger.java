package com.git.gestor_academico.swagger;

import com.git.gestor_academico.dtos.exceptions.CustomError;
import com.git.gestor_academico.dtos.request.TccRequestDTO;
import com.git.gestor_academico.dtos.response.TccResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "TCCs")
public interface TccControllerSwagger {

    @Operation(summary = "Buscar todos os TCCs")
    @ApiResponse(responseCode = "200", description = "TCCs retornados com sucesso",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TccResponseDTO.class)))
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
    ResponseEntity<List<TccResponseDTO>> getAll();

    @Operation(summary = "Buscar TCC pela matrícula")
    @ApiResponse(responseCode = "200", description = "TCC retornado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TccResponseDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "TCC com o ID especificado não foi encontrado",
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
    ResponseEntity<TccResponseDTO> procurarPorRegistro(@Parameter(description = "ID do TCC") Long id);

    @Operation(summary = "Salvar TCC")
    @ApiResponse(responseCode = "201", description = "TCC salvo com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TccResponseDTO.class))
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
    @ApiResponse(responseCode = "404", description = "Orientador/integrantes com a matrícula/RAs especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<TccResponseDTO> save(@Parameter(description = "Informações do TCC") TccRequestDTO tcc);

    @Operation(summary = "Atualizar TCC")
    @ApiResponse(responseCode = "200", description = "TCC atualizado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TccResponseDTO.class))
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
    @ApiResponse(responseCode = "404", description = "Orientador/integrantes com a matrícula/RAs especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<TccResponseDTO> atualizar(@Parameter(description = "ID do TCC") Long id,
                                             @Parameter(description = "Informações atualizadas do TCC") TccRequestDTO tcc);

    @Operation(summary = "Deletar TCC")
    @ApiResponse(responseCode = "204", description = "TCC deletado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TccResponseDTO.class))
            })
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário não autorizado para este recurso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "404", description = "O tcc com o ID especificado não foi encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<Void> deletar(@Parameter(description = "ID do TCC") Long id);

}
