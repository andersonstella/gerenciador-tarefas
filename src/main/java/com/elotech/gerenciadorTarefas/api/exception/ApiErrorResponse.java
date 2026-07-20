package com.elotech.gerenciadorTarefas.api.exception;

import java.time.LocalDateTime;

public record ApiErrorResponse(LocalDateTime dataHora, Integer status, String erro) {}