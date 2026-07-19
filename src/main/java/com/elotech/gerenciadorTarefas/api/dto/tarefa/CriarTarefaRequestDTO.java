package com.elotech.gerenciadorTarefas.api.dto.tarefa;

import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarTarefaRequestDTO(

    @NotBlank(message = "Título obrigatório")
    String titulo,

    String descricao,

    @NotNull(message = "Prioridade obrigatória")
    PrioridadeTarefa prioridade

) {}