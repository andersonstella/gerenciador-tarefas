package com.elotech.gerenciadorTarefas.api.controller;

import com.elotech.gerenciadorTarefas.api.dto.projeto.ProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.api.dto.tarefa.AlterarPrioridadeRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.tarefa.AtribuirResponsavelRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.tarefa.CriarTarefaRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.tarefa.FinalizarTarefaRequestDTO;
import com.elotech.gerenciadorTarefas.api.mapper.ProjetoApiMapper;
import com.elotech.gerenciadorTarefas.application.tarefa.TarefaApplicationService;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/projetos/{projetoId}/tarefas")
public class TarefaController {

    private final TarefaApplicationService tarefaApplicationService;

    public TarefaController(final TarefaApplicationService tarefaApplicationService) {
        this.tarefaApplicationService = tarefaApplicationService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@PathVariable final UUID projetoId,
            @Valid @RequestBody final CriarTarefaRequestDTO request) {

        final Projeto projeto = tarefaApplicationService.criarTarefa(projetoId, request.titulo(), request.descricao(),
                request.prioridade());

        return ResponseEntity.status(HttpStatus.CREATED).body(ProjetoApiMapper.toResponse(projeto));
    }

    @PutMapping("/{tarefaId}/iniciar")
    public ResponseEntity<ProjetoResponseDTO> iniciar(@PathVariable final UUID projetoId,
            @PathVariable final UUID tarefaId) {

        final Projeto projeto = tarefaApplicationService.iniciar(projetoId, tarefaId);

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @PutMapping("/{tarefaId}/finalizar")
    public ResponseEntity<ProjetoResponseDTO> finalizar(@PathVariable final UUID projetoId,
            @PathVariable final UUID tarefaId, @Valid @RequestBody final FinalizarTarefaRequestDTO request) {

        final Projeto projeto = tarefaApplicationService.finalizar(projetoId, tarefaId, request.usuarioId());

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @PutMapping("/{tarefaId}/responsavel")
    public ResponseEntity<ProjetoResponseDTO> atribuirResponsavel(@PathVariable final UUID projetoId,
            @PathVariable final UUID tarefaId, @Valid @RequestBody final AtribuirResponsavelRequestDTO request) {

        final Projeto projeto = tarefaApplicationService.atribuirResponsavel(projetoId, tarefaId, request.usuarioId());

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }

    @PutMapping("/{tarefaId}/prioridade")
    public ResponseEntity<ProjetoResponseDTO> alterarPrioridade(@PathVariable final UUID projetoId,
            @PathVariable final UUID tarefaId, @RequestBody final AlterarPrioridadeRequestDTO request) {

        final Projeto projeto = tarefaApplicationService.alterarPrioridade(projetoId, tarefaId, request.prioridade());

        return ResponseEntity.ok(ProjetoApiMapper.toResponse(projeto));
    }
}
