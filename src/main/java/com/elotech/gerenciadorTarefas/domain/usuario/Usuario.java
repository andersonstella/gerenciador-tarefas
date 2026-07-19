package com.elotech.gerenciadorTarefas.domain.usuario;

import ch.qos.logback.core.util.StringUtil;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import lombok.Getter;

import java.util.UUID;

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
     * Controla a alteracao de dados do usuario
     *
     * @param nome
     * @param email
     */
    public void alterarDados(String nome, String email) {

        validarNome(nome);
        validarEmail(email);

        this.nome = nome;
        this.email = email;
    }

    /**
     *
     * @param nome
     */
    private void validarNome(final String nome) {

        if (StringUtil.isNullOrEmpty(nome)) {
            throw new RegraNegocioException("Nome do usuário é obrigatório");
        }
    }

    /**
     *
     * @param email
     */
    private void validarEmail(final String email) {

        if (StringUtil.isNullOrEmpty(email)) {
            throw new RegraNegocioException("Email do usuário é obrigatório");
        }
    }

}
