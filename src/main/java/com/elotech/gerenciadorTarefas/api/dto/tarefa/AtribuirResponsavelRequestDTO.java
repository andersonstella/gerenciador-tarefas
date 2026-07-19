package com.elotech.gerenciadorTarefas.api.dto.tarefa;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AtribuirResponsavelRequestDTO(

    @NotNull(message = "Responsável obrigatório")
    UUID usuarioId

) {}