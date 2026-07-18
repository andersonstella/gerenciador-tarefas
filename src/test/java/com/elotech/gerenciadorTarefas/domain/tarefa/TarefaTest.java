package com.elotech.gerenciadorTarefas.domain.tarefa;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void naoDevePermitirTarefaDoneVoltarParaTodo() {

        Tarefa tarefa = new Tarefa("Criar API", "Implementar endpoint", PrioridadeTarefa.HIGH);
        tarefa.finalizarTarefa();

        Exception exception = assertThrows(RuntimeException.class, () -> tarefa.alterarStatus(StatusTarefa.TODO));
        assertEquals("Uma tarefa DONE não pode voltar para TODO", exception.getMessage());
    }

    @Test
    void deveFinalizarTarefa() {

        Tarefa tarefa = new Tarefa("Criar API", "Implementar endpoint", PrioridadeTarefa.HIGH);
        tarefa.finalizarTarefa();

        assertEquals(StatusTarefa.DONE, tarefa.getStatus());
    }

    @Test
    void deveAlterarStatusParaInProgress() {

        Tarefa tarefa = new Tarefa("Criar API", "Implementar endpoint", PrioridadeTarefa.HIGH);
        tarefa.alterarStatus(StatusTarefa.IN_PROGRESS);

        assertEquals(StatusTarefa.IN_PROGRESS, tarefa.getStatus());
    }

    @Test
    void deveFinalizarTarefaEAtualizarData() {

        Tarefa tarefa = new Tarefa("Criar API", "Implementar endpoint", PrioridadeTarefa.HIGH);

        LocalDateTime antes = tarefa.getAtualizadoEm();

        tarefa.finalizarTarefa();

        assertEquals(StatusTarefa.DONE, tarefa.getStatus());
        assertTrue(tarefa.getAtualizadoEm().isAfter(antes));
    }

}