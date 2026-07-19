package com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper;

import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.ProjetoEntity;

public final class ProjetoMapper {

    private ProjetoMapper() {
    }

    public static ProjetoEntity toEntity(final Projeto projeto) {

        ProjetoEntity entity = new ProjetoEntity(projeto.getId(), projeto.getNome());
        entity.setId(projeto.getId());
        entity.setNome(projeto.getNome());

        projeto.getTarefas().forEach(tarefa -> entity.adicionarTarefa(TarefaMapper.toEntity(tarefa)));

        projeto.getMembros().forEach(membro -> entity.adicionarMembro(MembroProjetoMapper.toEntity(membro)));

        return entity;
    }

    public static Projeto toDomain(final ProjetoEntity entity) {

        if (entity == null) {
            return null;
        }

        Projeto projeto = new Projeto(entity.getId(), entity.getNome());

        entity.getTarefas().forEach(tarefaEntity -> projeto.adicionarTarefa(TarefaMapper.toDomain(tarefaEntity)));
        entity.getMembros().forEach(membroEntity -> projeto.carregarMembro(MembroProjetoMapper.toDomain(membroEntity)));

        return projeto;
    }
}