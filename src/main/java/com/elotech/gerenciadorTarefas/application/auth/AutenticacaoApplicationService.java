package com.elotech.gerenciadorTarefas.application.auth;

import com.elotech.gerenciadorTarefas.application.usuario.UsuarioRepository;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import com.elotech.gerenciadorTarefas.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoApplicationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AutenticacaoApplicationService(final UsuarioRepository usuarioRepository,
            final PasswordEncoder passwordEncoder, final JwtService jwtService) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String autenticar(final String email, final String senha) {

        Usuario usuario = usuarioRepository.buscarPorEmail(email);

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RegraNegocioException("Email ou senha inválidos.");
        }

        return jwtService.gerarToken(usuario);
    }
}