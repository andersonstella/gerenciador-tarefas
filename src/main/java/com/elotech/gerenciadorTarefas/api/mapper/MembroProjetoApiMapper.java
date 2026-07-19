package com.elotech.gerenciadorTarefas.api.mapper;

import com.elotech.gerenciadorTarefas.api.dto.projeto.MembroProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.domain.projeto.MembroProjeto;

public final class MembroProjetoApiMapper {

    private MembroProjetoApiMapper() {
        //NA
    }


    public static MembroProjetoResponseDTO toResponse(final MembroProjeto membro) {

        if (membro == null) {
            return null;
        }

        return new MembroProjetoResponseDTO(membro.getId(), UsuarioApiMapper.toResponse(membro.getUsuario()), membro.getPapel());
    }
}