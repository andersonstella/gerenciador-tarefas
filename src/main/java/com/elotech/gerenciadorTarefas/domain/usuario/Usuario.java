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
    private String senha;

    /**
     * Contrutor para criação
     *
     * @param nome
     * @param email
     * @param senha
     */
    public Usuario(final String nome, final String email, final String senha) {

        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /**
     * Construtor para reconstrução
     * @param id
     * @param nome
     * @param email
     */
    public Usuario(final UUID id, final String nome, final String email, final String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    private void validarSenha(final String senha) {

        if (StringUtil.isNullOrEmpty(senha)) {
            throw new RegraNegocioException("Senha do usuário é obrigatória");
        }
    }

}
