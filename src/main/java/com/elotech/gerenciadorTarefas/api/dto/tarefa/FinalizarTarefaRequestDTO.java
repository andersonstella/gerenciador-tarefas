package com.elotech.gerenciadorTarefas.api.dto.tarefa;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FinalizarTarefaRequestDTO(@NotNull UUID usuarioId) {}