package com.elotech.gerenciadorTarefas.domain.projeto;

import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembrosProjeto {

    private Usuario usuario;
    private PapelMembro papel;

    /**
     * Contrutor
     */
    public MembrosProjeto() {
        //NA
    }

    /**
     * Boolena para saber se e admin
     * @return
     */
    public boolean isAdmin(){
        return papel == PapelMembro.ADMIN;
    }

}
