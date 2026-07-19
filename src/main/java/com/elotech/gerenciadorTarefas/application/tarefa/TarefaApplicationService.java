package com.elotech.gerenciadorTarefas.application.tarefa;

import com.elotech.gerenciadorTarefas.application.projeto.ProjetoRepository;
import com.elotech.gerenciadorTarefas.application.usuario.UsuarioRepository;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;

import java.util.Objects;
import java.util.UUID;

public class TarefaApplicationService {

    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Contrutor
     * @param projetoRepository
     * @param usuarioRepository
     */
    public TarefaApplicationService(final ProjetoRepository projetoRepository, final UsuarioRepository usuarioRepository) {
        this.projetoRepository = projetoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Projeto criarTarefa(final UUID projetoId, final String titulo, final String descricao, final PrioridadeTarefa prioridadeTarefa) {

        final Tarefa tarefa = new Tarefa(titulo, descricao, prioridadeTarefa);
        final Projeto projeto = projetoRepository.buscarPorId(projetoId);

        if (Objects.isNull(projeto)) {
            throw new RegraNegocioException("Projeto não encontrado.");
        }

        projeto.adicionarTarefa(tarefa);
        return projetoRepository.salvar(projeto);
    }

    public Projeto iniciar(final UUID projetoId, final UUID tarefaId) {

        final Projeto projeto = projetoRepository.buscarPorId(projetoId);
        projeto.iniciarTarefa(tarefaId);

        return projetoRepository.salvar(projeto);
    }

    public Projeto finalizar(final UUID projetoId, final UUID tarefaId, final UUID usuarioId) {

        final Projeto projeto = projetoRepository.buscarPorId(projetoId);
        final Usuario usuario = usuarioRepository.buscarPorId(usuarioId);

        projeto.finalizarTarefa(tarefaId, usuario);

        return projetoRepository.salvar(projeto);
    }

    public Projeto atribuirResponsavel(final UUID projetoId, final UUID tarefaId, final UUID usuarioId) {

        final Projeto projeto = projetoRepository.buscarPorId(projetoId);
        final Usuario usuario = usuarioRepository.buscarPorId(usuarioId);

        projeto.atribuirResponsavel(tarefaId, usuario);

        return projetoRepository.salvar(projeto);
    }

    public Projeto alterarPrioridade(final UUID projetoId, final UUID tarefaId, final PrioridadeTarefa prioridadeTarefa) {

        final Projeto projeto = projetoRepository.buscarPorId(projetoId);
        projeto.alterarPrioridade(tarefaId, prioridadeTarefa);

        return projetoRepository.salvar(projeto);
    }

}
