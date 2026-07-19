package com.elotech.gerenciadorTarefas.application.tarefa;

import com.elotech.gerenciadorTarefas.application.projeto.ProjetoRepository;
import com.elotech.gerenciadorTarefas.application.usuario.UsuarioRepository;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TarefaApplicationService {

    private final ProjetoRepository projetoRepository;

    private final UsuarioRepository usuarioRepository;

    public TarefaApplicationService(final ProjetoRepository projetoRepository,
            final UsuarioRepository usuarioRepository) {
        this.projetoRepository = projetoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Projeto criarTarefa(final UUID projetoId, final String titulo, final String descricao,
            final PrioridadeTarefa prioridadeTarefa) {

        final Projeto projeto = buscarProjetoPorId(projetoId);
        final Tarefa tarefa = new Tarefa(titulo, descricao, prioridadeTarefa);

        projeto.adicionarTarefa(tarefa);

        return projetoRepository.salvar(projeto);
    }

    public Projeto iniciar(final UUID projetoId, final UUID tarefaId) {

        final Projeto projeto = buscarProjetoPorId(projetoId);

        projeto.iniciarTarefa(tarefaId);

        return projetoRepository.salvar(projeto);
    }

    public Projeto finalizar(final UUID projetoId, final UUID tarefaId, final UUID usuarioId) {

        final Projeto projeto = buscarProjetoPorId(projetoId);
        final Usuario usuario = buscarUsuarioPorId(usuarioId);

        projeto.finalizarTarefa(tarefaId, usuario);

        return projetoRepository.salvar(projeto);
    }

    public Projeto atribuirResponsavel(final UUID projetoId, final UUID tarefaId, final UUID usuarioId) {

        final Projeto projeto = buscarProjetoPorId(projetoId);
        final Usuario usuario = buscarUsuarioPorId(usuarioId);

        projeto.atribuirResponsavel(tarefaId, usuario);

        return projetoRepository.salvar(projeto);
    }

    public Projeto alterarPrioridade(final UUID projetoId, final UUID tarefaId,
            final PrioridadeTarefa prioridadeTarefa) {

        final Projeto projeto = buscarProjetoPorId(projetoId);

        projeto.alterarPrioridade(tarefaId, prioridadeTarefa);

        return projetoRepository.salvar(projeto);
    }

    private Projeto buscarProjetoPorId(final UUID projetoId) {

        if (projetoId == null) {
            throw new RegraNegocioException("Id do projeto é obrigatório.");
        }

        return projetoRepository.buscarPorId(projetoId);
    }

    private Usuario buscarUsuarioPorId(final UUID usuarioId) {

        if (usuarioId == null) {
            throw new RegraNegocioException("Id do usuário é obrigatório.");
        }

        return usuarioRepository.buscarPorId(usuarioId);
    }
}