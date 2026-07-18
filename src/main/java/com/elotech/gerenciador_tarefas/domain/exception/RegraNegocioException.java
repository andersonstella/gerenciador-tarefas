package com.elotech.gerenciador_tarefas.domain.exception;

public class RegraNegocioException extends RuntimeException {

    /**
     * Centralizador das exceptions para as regras de negocio
     * @param pMessage
     */
    public RegraNegocioException(String pMessage) {
        super(pMessage);
    }
}
