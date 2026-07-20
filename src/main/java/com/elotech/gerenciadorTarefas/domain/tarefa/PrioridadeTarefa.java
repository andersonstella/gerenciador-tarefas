package com.elotech.gerenciadorTarefas.domain.tarefa;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Prioridade da tarefa.")
public enum PrioridadeTarefa {

    LOW,
    MEDIUM,
    HIGH,
    CRITICAL

}
