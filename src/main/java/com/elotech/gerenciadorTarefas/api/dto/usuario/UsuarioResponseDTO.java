package com.elotech.gerenciadorTarefas.api.dto.usuario;

import java.util.UUID;

public record UsuarioResponseDTO(

    UUID id,
    String nome,
    String email

) {}