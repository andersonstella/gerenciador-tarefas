package com.elotech.gerenciadorTarefas.api.mapper;

import com.elotech.gerenciadorTarefas.api.dto.projeto.MembroProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.api.dto.projeto.ProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.api.dto.tarefa.TarefaResponseDTO;
import com.elotech.gerenciadorTarefas.domain.projeto.MembroProjeto;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;

import java.util.ArrayList;
import java.util.List;

public final class ProjetoApiMapper {

    private ProjetoApiMapper() {
    }


    public static ProjetoResponseDTO toResponse(
        final Projeto projeto) {

        if (projeto == null) {
            return null;
        }

        List<TarefaResponseDTO> tarefas = new ArrayList<>();
        for (Tarefa tarefa : projeto.getTarefas()) {
            tarefas.add(TarefaApiMapper.toResponse(tarefa));
        }

        List<MembroProjetoResponseDTO> membros = new ArrayList<>();
        for (MembroProjeto membro : projeto.getMembros()) {
            membros.add(MembroProjetoApiMapper.toResponse(membro));
        }

        return new ProjetoResponseDTO(projeto.getId(), projeto.getNome(), tarefas, membros);
    }
}