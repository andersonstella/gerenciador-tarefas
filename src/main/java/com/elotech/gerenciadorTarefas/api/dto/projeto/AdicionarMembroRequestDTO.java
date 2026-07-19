package com.elotech.gerenciadorTarefas.api.dto.projeto;

import com.elotech.gerenciadorTarefas.domain.projeto.PapelMembro;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AdicionarMembroRequestDTO(

    @NotNull(message = "Usuário obrigatório")
    UUID usuarioId,

    @NotNull(message = "Papel obrigatório")
    PapelMembro papel

) {}