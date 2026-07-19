package com.elotech.gerenciadorTarefas.infrastructure.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(final String mensagem) {
        super(mensagem);
    }
}