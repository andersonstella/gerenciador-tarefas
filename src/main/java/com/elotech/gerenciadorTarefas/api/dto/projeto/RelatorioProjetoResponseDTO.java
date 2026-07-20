package com.elotech.gerenciadorTarefas.api.dto.projeto;

import java.util.Map;

public class RelatorioProjetoResponseDTO {

    private final Map<String, Long> byStatus;

    private final Map<String, Long> byPriority;

    public RelatorioProjetoResponseDTO(final Map<String, Long> byStatus, final Map<String, Long> byPriority) {

        this.byStatus = byStatus;
        this.byPriority = byPriority;
    }

    public Map<String, Long> getByStatus() {
        return byStatus;
    }

    public Map<String, Long> getByPriority() {
        return byPriority;
    }
}