package com.elotech.gerenciadorTarefas.api.dto.projeto;

import jakarta.validation.constraints.NotBlank;

public record CriarProjetoRequestDTO(

    @NotBlank(message = "Nome do projeto é obrigatório")
    String nome

) {}