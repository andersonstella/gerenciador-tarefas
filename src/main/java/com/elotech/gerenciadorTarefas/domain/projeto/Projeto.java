package com.elotech.gerenciadorTarefas.domain.projeto;

import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.tarefa.StatusTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Projeto {

    private List<MembrosProjeto> membros;
    private List<Tarefa> tarefas;

    /**
     * Contrutor
     */
    public Projeto() {
        this.membros = new ArrayList<>();
        this.tarefas = new ArrayList<>();
    }

    /**
     * Metodo responsavel por controlar a finalizacao de uma tarefa
     *
     * @param pTarefa
     * @param pUsuario
     */
    public void finalizarTarefa(final Tarefa pTarefa, final Usuario pUsuario) {

        final MembrosProjeto membro = buscarMembro(pUsuario);

        if (pTarefa.isCritical() && !membro.isAdmin()) {
            throw new RegraNegocioException("Somente ADMIN pode finalizar tarefas CRITICAL");
        }
        pTarefa.finalizarTarefa();
    }

    /**
     * Metodo que vai validar se a tarefa pode ser iniciada antes de iniciar
     * @param pTarefa
     */
    public void iniciarTarefa(final Tarefa pTarefa) {

        validarLimiteWip(pTarefa);
        pTarefa.iniciarTarefa();
    }

    /**
     * Metodo que busca os membros do projeto
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

}
