package com.elotech.gerenciadorTarefas.domain.tarefa;

import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Tarefa {

    private UUID id;

    private String titulo;

    private String descricao;

    private StatusTarefa status;

    private PrioridadeTarefa prioridade;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private LocalDate prazo;

    private Usuario responsavel;

    /**
     * Contrutor para criação
     *
     * @param titulo
     * @param descricao
     * @param prioridade
     */
    public Tarefa(final String titulo, final String descricao, final PrioridadeTarefa prioridade) {

        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = StatusTarefa.TODO;
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();

    }

    /**
     * Construtor para reconstrção
     * @param id
     * @param titulo
     * @param descricao
     * @param prioridade
     */
    public Tarefa(final UUID id, final String titulo, final String descricao, final PrioridadeTarefa prioridade) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = StatusTarefa.TODO;
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();

    }

    /**
     * Metodo que controla a transicao de status de uma tarefa
     *
     * @param pNovoStatus
     */
    public void alterarStatus(final StatusTarefa pNovoStatus) {

        validarTransicaoStatus(pNovoStatus);

        this.status = pNovoStatus;
        this.atualizadoEm = LocalDateTime.now();
    }

    /**
     * Metodo que inicia uma tarefa
     */
    public void iniciarTarefa() {
        alterarStatus(StatusTarefa.IN_PROGRESS);
    }

    /**
     * Metodo que finaliza uma tarefa
     */
    public void finalizarTarefa() {
        alterarStatus(StatusTarefa.DONE);
    }

    /**
     * Metodo que valida tarefas criticas
     *
     * @return
     */
    public boolean isCritical() {
        return prioridade == PrioridadeTarefa.CRITICAL;
    }

    public void atribuirResponsavel(final Usuario usuario) {

        if (usuario == null) {
            throw new RegraNegocioException("Responsável é obrigatório.");
        }
        this.responsavel = usuario;
    }

    public void alterarPrioridade(final PrioridadeTarefa prioridade) {

        if (prioridade == null) {
            throw new RegraNegocioException("A prioridade é obrigatória.");
        }
        this.prioridade = prioridade;
    }

    /**
     * Metodo responsavel por validar a transicao de status.
     * Nãao e permitido uma tarefa DONE voltar para TODO
     *
     * @param pNovoStatus
     */
    private void validarTransicaoStatus(final StatusTarefa pNovoStatus) {

        if (this.status == StatusTarefa.DONE && pNovoStatus == StatusTarefa.TODO) {
            throw new RegraNegocioException("Uma tarefa DONE não pode voltar para TODO");
        }
    }

}
