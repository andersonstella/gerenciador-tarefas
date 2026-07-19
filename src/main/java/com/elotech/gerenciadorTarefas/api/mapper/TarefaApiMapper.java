package com.elotech.gerenciadorTarefas.api.mapper;

import com.elotech.gerenciadorTarefas.api.dto.tarefa.TarefaResponseDTO;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;

public final class TarefaApiMapper {

    private TarefaApiMapper() {
        //NA
    }


    public static TarefaResponseDTO toResponse(final Tarefa tarefa) {

        if (tarefa == null) {
            return null;
        }

        return new TarefaResponseDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getPrioridade(),
            tarefa.getCriadoEm(), tarefa.getAtualizadoEm(), tarefa.getPrazo(), UsuarioApiMapper.toResponse(tarefa.getResponsavel()));
    }
}