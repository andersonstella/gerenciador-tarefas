package com.elotech.gerenciadorTarefas.application.usuario;

import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioApplicationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioApplicationService(final UsuarioRepository usuarioRepository, final PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criar(final String nome, final String email, final String senha) {

        final String senhaCriptografada = passwordEncoder.encode(senha);

        Usuario usuario = new Usuario(nome, email, senhaCriptografada);
        return usuarioRepository.salvar(usuario);
    }

    public Usuario buscarPorId(final UUID pIdUsuario) {

        if (pIdUsuario == null) {
            throw new RegraNegocioException("Id do usuário é obrigatório.");
        }
        return usuarioRepository.buscarPorId(pIdUsuario);
    }

    public void remover(UUID usuarioId) {

        if(usuarioId == null){
            throw new IllegalArgumentException("Usuário obrigatório");
        }
        usuarioRepository.remover(usuarioId);
    }

    public List<Usuario> listar() {

        return usuarioRepository.buscarTodos();
    }


}
