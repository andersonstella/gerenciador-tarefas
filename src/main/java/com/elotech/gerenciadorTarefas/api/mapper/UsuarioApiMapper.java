package com.elotech.gerenciadorTarefas.api.mapper;

import com.elotech.gerenciadorTarefas.api.dto.usuario.UsuarioResponseDTO;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;

public final class UsuarioApiMapper {

    private UsuarioApiMapper() {
        //NA
    }

    public static UsuarioResponseDTO toResponse(final Usuario usuario) {

        if (usuario == null) {
            return null;
        }

        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}