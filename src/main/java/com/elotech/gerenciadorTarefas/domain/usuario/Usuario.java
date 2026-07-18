package com.elotech.gerenciadorTarefas.domain.usuario;

import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Usuario {

    private UUID id;

    private String nome;

    private String email;

    /**
     * Contrutor
     *
     * @param pNome
     * @param pEmail
     */
    public Usuario(final String pNome, final String pEmail) {

        validarNome(pNome);
        validarEmail(pEmail);

        this.id = UUID.randomUUID();
        this.nome = pNome;
        this.email = pEmail;
    }

    /**
     *
     * @param pNome
     */
    private void validarNome(final String pNome) {

        if (pNome == null || pNome.isBlank()) {
            throw new RegraNegocioException("Nome do usuário é obrigatório");
        }
    }

    /**
     *
     * @param pEmail
     */
    private void validarEmail(final String pEmail) {

        if (pEmail == null || pEmail.isBlank()) {
            throw new RegraNegocioException("Email do usuário é obrigatório");
        }
    }

}
