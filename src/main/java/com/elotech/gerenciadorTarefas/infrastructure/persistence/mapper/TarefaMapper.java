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
        entity.setId(tarefa.getId());
        entity.setTitulo(tarefa.getTitulo());
        entity.setDescricao(tarefa.getDescricao());
        entity.setStatus(tarefa.getStatus());
        entity.setPrioridade(tarefa.getPrioridade());
        entity.setCriadoEm(tarefa.getCriadoEm());
        entity.setAtualizadoEm(tarefa.getAtualizadoEm());
        entity.setPrazo(tarefa.getPrazo());

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

        return tarefa;
    }
}