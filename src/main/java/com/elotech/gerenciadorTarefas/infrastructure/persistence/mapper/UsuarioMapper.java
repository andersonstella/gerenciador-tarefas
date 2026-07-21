package com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper;

import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.UsuarioEntity;

import java.util.UUID;

public final class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static UsuarioEntity toEntity(final Usuario usuario) {

        if (usuario == null) {
            return null;
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setSenha(usuario.getSenha());

        return entity;
    }

    public static Usuario toDomain(final UsuarioEntity entity) {

        if (entity == null) {
            return null;
        }
        return new Usuario(entity.getId(), entity.getNome(), entity.getEmail(), entity.getSenha());
    }
}