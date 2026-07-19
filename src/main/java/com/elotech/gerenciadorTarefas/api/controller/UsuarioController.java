package com.elotech.gerenciadorTarefas.api.controller;

import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioResponseDTO;
import com.elotech.gerenciadorTarefas.api.mapper.UsuarioApiMapper;
import com.elotech.gerenciadorTarefas.application.usuario.UsuarioApplicationService;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioApplicationService usuarioApplicationService;

    public UsuarioController(final UsuarioApplicationService usuarioApplicationService) {
        this.usuarioApplicationService = usuarioApplicationService;
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody final UsuarioRequestDTO request) {

        final Usuario usuario = usuarioApplicationService.criar(request.nome(), request.email());

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioApiMapper.toResponse(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable final UUID id) {

        final Usuario usuario = usuarioApplicationService.buscarPorId(id);

        return ResponseEntity.ok(UsuarioApiMapper.toResponse(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable final UUID id) {

        usuarioApplicationService.remover(id);

        return ResponseEntity.noContent().build();
    }
}