package com.elotech.gerenciador_tarefas.domain.usuario;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Usuario {

    private UUID id;

    private String nome;

    private String email;


}
