package com.elotech.gerenciadorTarefas.api.dto.projeto;

import com.elotech.gerenciadorTarefas.api.dto.tarefa.TarefaResponseDTO;

import java.util.List;
import java.util.UUID;

public record ProjetoResponseDTO(

    UUID id,
    String nome,
    List<TarefaResponseDTO> tarefas,
    List<MembroProjetoResponseDTO> membros

) {}