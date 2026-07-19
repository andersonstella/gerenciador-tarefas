package com.elotech.gerenciadorTarefas.domain.projeto;

import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.StatusTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.Tarefa;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjetoTest {

    @Test
    void devePermitirAdminFinalizarCritical() {

        Usuario usuario = new Usuario("Silva", "silva@gmail.com");

        Tarefa tarefa = new Tarefa("Criar API", "Implementar endpoint", PrioridadeTarefa.CRITICAL);
        tarefa.setResponsavel(usuario);

        MembrosProjeto membrosProjeto = new MembrosProjeto();
        membrosProjeto.setUsuario(usuario);
        membrosProjeto.setPapel(PapelMembro.ADMIN);
        
        Projeto projeto = new Projeto();
        projeto.getMembros().add(membrosProjeto);
        projeto.getTarefas().add(tarefa);

        projeto.finalizarTarefa(tarefa.getId(), usuario);

        assertEquals(StatusTarefa.DONE, tarefa.getStatus());

    }

    @Test
    void naoDevePermitirMemberFinalizarCritical() {

        Usuario usuario = new Usuario("Silva", "silva@gmail.com");

        Tarefa tarefa = new Tarefa("Criar API", "Implementar endpoint", PrioridadeTarefa.CRITICAL);

        tarefa.setResponsavel(usuario);

        MembrosProjeto membrosProjeto = new MembrosProjeto();
        membrosProjeto.setUsuario(usuario);
        membrosProjeto.setPapel(PapelMembro.MEMBER);

        Projeto projeto = new Projeto();
        projeto.getMembros().add(membrosProjeto);
        projeto.getTarefas().add(tarefa);

        assertThrows(RegraNegocioException.class, () -> projeto.finalizarTarefa(tarefa.getId(), usuario));
    }
}
