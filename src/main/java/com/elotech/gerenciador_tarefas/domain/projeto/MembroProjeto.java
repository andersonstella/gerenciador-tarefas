package com.elotech.gerenciador_tarefas.domain.projeto;

import com.elotech.gerenciador_tarefas.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembroProjeto {

    private Usuario usuario;

    private PapelMembro papel;

    /**
     * Boolena para saber se e admin
     * @return
     */
    public boolean isAdmin(){
        return papel == PapelMembro.ADMIN;
    }

}
