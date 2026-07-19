package com.elotech.gerenciadorTarefas.api.dto.tarefa;

import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import jakarta.validation.constraints.NotNull;

public record AlterarPrioridadeRequestDTO(

    @NotNull(message = "Prioridade obrigatória")
    PrioridadeTarefa prioridade

) {}