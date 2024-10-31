package com.git.gestor_academico.swagger;

import com.git.gestor_academico.dtos.exceptions.CustomError;
import com.git.gestor_academico.dtos.request.AuthenticationRequest;
import com.git.gestor_academico.dtos.request.CheckpointRequestDTO;
import com.git.gestor_academico.dtos.response.AuthenticationResponse;
import com.git.gestor_academico.dtos.response.CheckpointResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação")
public interface AuthControllerSwagger {

    @Operation(summary = "Autenticar")
    @ApiResponse(responseCode = "200", description = "Autenticado com sucesso",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class))
            })
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "403", description = "Usuário ou senha inválido",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))
            })
    ResponseEntity<AuthenticationResponse> createAuthenticationToken(@Parameter(description = "Dados de acesso") AuthenticationRequest checkpoint);

}
