package com.elotech.gerenciadorTarefas.api.dto.projeto;

import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioResponseDTO;
import com.elotech.gerenciadorTarefas.domain.projeto.PapelMembro;

import java.util.UUID;

public record MembroProjetoResponseDTO(

    UUID id,
    UsuarioResponseDTO usuario,
    PapelMembro papel

) {}