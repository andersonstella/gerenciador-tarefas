package com.elotech.gerenciadorTarefas.domain.projeto;

import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MembroProjeto {

    private UUID id;
    private Usuario usuario;
    private PapelMembro papel;

    /**
     * Contrutor
     */
    public MembroProjeto() {
        //NA
    }

    /**
     * Contrutor
     * @param usuario
     * @param papel
     */
    public MembroProjeto(final Usuario usuario, final PapelMembro papel) {
        this.usuario = usuario;
        this.papel = papel;
    }

    /**
     * Boolena para saber se e admin
     * @return
     */
    public boolean isAdmin(){
        return papel == PapelMembro.ADMIN;
    }

}
