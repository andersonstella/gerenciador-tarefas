package com.elotech.gerenciadorTarefas.api.exception;

import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.infrastructure.exception.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiErrorResponse> tratarRegraNegocio(final RegraNegocioException ex) {

        ApiErrorResponse erro = new ApiErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> tratarRegistroNaoEncontrado(final RegistroNaoEncontradoException ex) {

        ApiErrorResponse erro = new ApiErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> tratarException(final Exception ex) {

        ApiErrorResponse erro = new ApiErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno da aplicação.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}