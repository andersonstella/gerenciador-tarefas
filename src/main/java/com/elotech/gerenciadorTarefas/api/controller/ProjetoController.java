package com.elotech.gerenciadorTarefas.api.controller;

import com.elotech.gerenciadorTarefas.api.dto.projeto.AdicionarMembroRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.projeto.CriarProjetoRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.projeto.ProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.api.mapper.ProjetoApiMapper;
import com.elotech.gerenciadorTarefas.application.projeto.ProjetoApplicationService;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoApplicationService projetoApplicationService;

    public ProjetoController(final ProjetoApplicationService projetoApplicationService) {

        this.projetoApplicationService = projetoApplicationService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@Valid @RequestBody final CriarProjetoRequestDTO request) {

        final Projeto projeto = projetoApplicationService.criarProjeto(request.nome());

        return ResponseEntity.status(HttpStatus.CREATED).body(ProjetoApiMapper.toResponse(projeto));
    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable final UUID projetoId) {

        final Projeto projeto = projetoApplicationService.buscarPorId(projetoId);

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @PutMapping("/{projetoId}/membros")
    public ResponseEntity<ProjetoResponseDTO> adicionarMembro(@PathVariable final UUID projetoId,
            @Valid @RequestBody final AdicionarMembroRequestDTO request) {

        final Projeto projeto = projetoApplicationService.adicionarMembro(projetoId, request.usuarioId(),
                request.papel());

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @DeleteMapping("/{projetoId}/membros/{usuarioId}")
    public ResponseEntity<Void> removerMembro(@PathVariable final UUID projetoId, @PathVariable final UUID usuarioId) {

        projetoApplicationService.removerMembro(projetoId, usuarioId);

        return ResponseEntity.noContent().build();
    }
}