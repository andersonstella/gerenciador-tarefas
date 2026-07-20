package com.elotech.gerenciadorTarefas.api.controller;

import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioResponseDTO;
import com.elotech.gerenciadorTarefas.api.mapper.UsuarioApiMapper;
import com.elotech.gerenciadorTarefas.application.usuario.UsuarioApplicationService;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Usuários", description = "Gerenciamento de usuários.")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioApplicationService usuarioApplicationService;

    public UsuarioController(final UsuarioApplicationService usuarioApplicationService) {
        this.usuarioApplicationService = usuarioApplicationService;
    }

    @Operation(summary = "Criar usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody final UsuarioRequestDTO request) {

        final Usuario usuario = usuarioApplicationService.criar(request.nome(), request.email());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UsuarioApiMapper.toResponse(usuario));
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable final UUID id) {

        final Usuario usuario = usuarioApplicationService.buscarPorId(id);

        return ResponseEntity.ok(UsuarioApiMapper.toResponse(usuario));
    }

    @GetMapping
    @Operation(summary = "Listar usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários encontrados")
    })
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {

        final List<UsuarioResponseDTO> usuarios = usuarioApplicationService.listar()
                .stream()
                .map(UsuarioApiMapper::toResponse)
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Remover usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable final UUID id) {

        usuarioApplicationService.remover(id);

        return ResponseEntity.noContent().build();
    }
}