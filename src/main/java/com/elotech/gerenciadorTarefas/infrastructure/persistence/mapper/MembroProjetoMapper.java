package com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper;

import com.elotech.gerenciadorTarefas.domain.projeto.MembroProjeto;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.MembroProjetoEntity;

public final class MembroProjetoMapper {

    private MembroProjetoMapper() {
    }


    public static MembroProjetoEntity toEntity(final MembroProjeto membro) {

        if (membro == null) {
            return null;
        }

        MembroProjetoEntity entity = new MembroProjetoEntity(membro.getId(), membro.getPapel());

        if (membro.getUsuario() != null) {
            entity.setUsuario(UsuarioMapper.toEntity(membro.getUsuario()));
        }

        return entity;
    }


    public static MembroProjeto toDomain(final MembroProjetoEntity entity) {

        if (entity == null) {
            return null;
        }

        MembroProjeto membro = new MembroProjeto();

        membro.setId(entity.getId());
        membro.setPapel(entity.getPapel());

        if (entity.getUsuario() != null) {
            membro.setUsuario(
                UsuarioMapper.toDomain(entity.getUsuario())
            );
        }

        return membro;
    }
}