package com.elotech.gerenciadorTarefas.api.controller;

import com.elotech.gerenciadorTarefas.api.dto.projeto.AdicionarMembroRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.projeto.CriarProjetoRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.projeto.ProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.api.dto.projeto.RelatorioProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.api.mapper.ProjetoApiMapper;
import com.elotech.gerenciadorTarefas.application.projeto.ProjetoApplicationService;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Projetos", description = "Gerenciamento de projetos.")
@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoApplicationService projetoApplicationService;

    public ProjetoController(final ProjetoApplicationService projetoApplicationService) {

        this.projetoApplicationService = projetoApplicationService;
    }

    @Operation(summary = "Criar projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@Valid @RequestBody final CriarProjetoRequestDTO request) {

        final Projeto projeto = projetoApplicationService.criarProjeto(request.nome());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProjetoApiMapper.toResponse(projeto));
    }

    @Operation(summary = "Buscar projeto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto encontrado"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado")
    })
    @GetMapping("/{projetoId}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable final UUID projetoId) {

        final Projeto projeto = projetoApplicationService.buscarPorId(projetoId);

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @Operation(summary = "Adicionar membro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membro adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Projeto ou usuário não encontrado")
    })
    @PutMapping("/{projetoId}/membros")
    public ResponseEntity<ProjetoResponseDTO> adicionarMembro(@PathVariable final UUID projetoId,
            @Valid @RequestBody final AdicionarMembroRequestDTO request) {

        final Projeto projeto = projetoApplicationService.adicionarMembro(projetoId, request.usuarioId(),
                request.papel());

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @Operation(summary = "Remover membro")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Membro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto ou usuário não encontrado")
    })
    @DeleteMapping("/{projetoId}/membros/{usuarioId}")
    public ResponseEntity<Void> removerMembro(@PathVariable final UUID projetoId, @PathVariable final UUID usuarioId) {

        projetoApplicationService.removerMembro(projetoId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Gera relatório resumido do projeto")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida") })
    @GetMapping("/{projetoId}/relatorio")
    public ResponseEntity<RelatorioProjetoResponseDTO> gerarRelatorio(@PathVariable final UUID projetoId) {

        return ResponseEntity.ok(projetoApplicationService.gerarRelatorio(projetoId));
    }
}