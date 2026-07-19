package com.elotech.gerenciadorTarefas.application.usuario;

import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;

import java.util.UUID;

public class UsuarioApplicationService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioApplicationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criar(final String nome, final String email) {

        Usuario usuario = new Usuario(nome, email);
        return usuarioRepository.salvar(usuario);
    }

    public Usuario atualizar(UUID usuarioId, String nome, String email) {

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId);
        usuario.alterarDados(nome, email);

        return usuarioRepository.salvar(usuario);
    }

    public void remover(UUID usuarioId) {

        if(usuarioId == null){
            throw new IllegalArgumentException("Usuário obrigatório");
        }
        usuarioRepository.remover(usuarioId);
    }

}
