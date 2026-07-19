package com.elotech.gerenciadorTarefas.api.dto.tarefa;

import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioResponseDTO;
import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.StatusTarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TarefaResponseDTO(

    UUID id,
    String titulo,
    String descricao,
    StatusTarefa status,
    PrioridadeTarefa prioridade,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm,
    LocalDate prazo,
    UsuarioResponseDTO responsavel

) {}