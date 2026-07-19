package com.elotech.gerenciadorTarefas.application.projeto;

import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;

import java.util.UUID;

public interface ProjetoRepository {

    Projeto salvar(Projeto projeto);

    Projeto buscarPorId(UUID id);

    void remover(UUID id);
}
