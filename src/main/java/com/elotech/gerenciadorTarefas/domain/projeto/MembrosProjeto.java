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
     * Contrutor
     * @param usuario
     * @param papel
     */
    public MembrosProjeto(final Usuario usuario, final PapelMembro papel) {
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
