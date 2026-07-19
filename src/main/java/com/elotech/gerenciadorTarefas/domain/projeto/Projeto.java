package com.elotech.gerenciadorTarefas.domain.projeto;

import ch.qos.logback.core.util.StringUtil;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.StatusTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Projeto {

    private UUID id;
    private String nome;
    private final List<MembrosProjeto> membros = new ArrayList<>();
    private final List<Tarefa> tarefas = new ArrayList<>();
    /**
     * Contrutor
     */
    protected Projeto() {
        //NA
    }

    /**
     * Contrutor
     */
    public Projeto(final String nome) {

        if (StringUtil.isNullOrEmpty(nome)) {
            throw new RegraNegocioException("Nome do projeto é obrigatório.");
        }

        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    /**
     * Metodo responsavel por controlar a finalizacao de uma tarefa
     *
     * @param tarefaId
     * @param usuario
     */
    public void finalizarTarefa(final UUID tarefaId, final Usuario usuario) {

        final Tarefa tarefa = buscarTarefa(tarefaId);
        final MembrosProjeto membro = buscarMembro(usuario);

        if (tarefa.isCritical() && !membro.isAdmin()) {
            throw new RegraNegocioException("Somente ADMIN pode finalizar tarefas CRITICAL");
        }
        tarefa.finalizarTarefa();
    }

    /**
     * Metodo que vai validar se a tarefa pode ser iniciada antes de iniciar
     *
     * @param tarefaId
     */
    public void iniciarTarefa(final UUID tarefaId) {

        final Tarefa tarefa = buscarTarefa(tarefaId);

        validarLimiteWip(tarefa);

        tarefa.iniciarTarefa();
    }

    public void adicionarTarefa(final Tarefa tarefa) {

        if (Objects.isNull(tarefa)) {
            throw new RegraNegocioException("A tarefa é obrigatória.");
        }
        this.tarefas.add(tarefa);
    }

    public void atribuirResponsavel(final UUID tarefaId, final Usuario usuario) {

        final Tarefa tarefa = buscarTarefa(tarefaId);

        buscarMembro(usuario);
        tarefa.atribuirResponsavel(usuario);
    }

    public void alterarPrioridade(final UUID tarefaId, final PrioridadeTarefa prioridade) {

        final Tarefa tarefa = buscarTarefa(tarefaId);
        tarefa.alterarPrioridade(prioridade);
    }

    public void adicionarMembro(final Usuario usuario, final PapelMembro papel) {

        if (usuario == null) {
            throw new RegraNegocioException("Usuário é obrigatório.");
        }

        final boolean jaExiste = membros.stream()
            .anyMatch(m -> m.getUsuario().getId().equals(usuario.getId()));

        if (jaExiste) {
            throw new RegraNegocioException("Usuário já é membro do projeto.");
        }

        final MembrosProjeto membro = new MembrosProjeto(usuario, papel);
        membros.add(membro);
    }

    public void removerMembro(final UUID usuarioId) {

        boolean removido = membros.removeIf(membro -> membro.getUsuario().getId().equals(usuarioId));

        if (!removido) {
            throw new RegraNegocioException("Membro não encontrado.");
        }
    }

    /**
     * Metodo que busca os membros do projeto
     *
     * @param pUsuario
     * @return
     */
    private MembrosProjeto buscarMembro(final Usuario pUsuario) {

        return membros.stream()
            .filter(m -> m.getUsuario().equals(pUsuario))
            .findFirst()
            .orElseThrow(() -> new RegraNegocioException("Usuário não pertence ao projeto")
            );
    }

    /**
     * Metodo responsavel por validar o limite de 5 tarefas em andamento
     *
     * @param pTarefa
     */
    private void validarLimiteWip(final Tarefa pTarefa) {

        long quantidade = tarefas.stream()
            .filter(t -> t.getResponsavel().equals(pTarefa.getResponsavel()))
            .filter(t -> t.getStatus() == StatusTarefa.IN_PROGRESS)
            .count();

        if (quantidade >= 5) {
            throw new RegraNegocioException("Usuário já possui 5 tarefas em andamento");
        }
    }

    private Tarefa buscarTarefa(final UUID tarefaId) {

        return tarefas.stream()
            .filter(t -> t.getId().equals(tarefaId))
            .findFirst()
            .orElseThrow(() -> new RegraNegocioException("Tarefa não encontrada."));
    }

}
