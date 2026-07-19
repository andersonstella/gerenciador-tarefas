package com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper;

import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.TarefaEntity;

public final class TarefaMapper {

    private TarefaMapper() {
    }

    public static TarefaEntity toEntity(final Tarefa tarefa) {

        if (tarefa == null) {
            return null;
        }

        TarefaEntity entity = new TarefaEntity(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus(),
            tarefa.getPrioridade(), tarefa.getCriadoEm(), tarefa.getAtualizadoEm(), tarefa.getPrazo());

        if (tarefa.getResponsavel() != null) {
            entity.setResponsavel(UsuarioMapper.toEntity(tarefa.getResponsavel()));
        }

        return entity;
    }

    public static Tarefa toDomain(final TarefaEntity entity) {

        if (entity == null) {
            return null;
        }

        Tarefa tarefa = new Tarefa(entity.getId(), entity.getTitulo(), entity.getDescricao(), entity.getPrioridade());

        tarefa.setStatus(entity.getStatus());
        tarefa.setCriadoEm(entity.getCriadoEm());
        tarefa.setAtualizadoEm(entity.getAtualizadoEm());
        tarefa.setPrazo(entity.getPrazo());

        if (entity.getResponsavel() != null) {
            tarefa.setResponsavel(UsuarioMapper.toDomain(entity.getResponsavel()));
        }

        return tarefa;
    }
}