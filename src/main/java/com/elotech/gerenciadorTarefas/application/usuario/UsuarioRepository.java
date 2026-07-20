package com.elotech.gerenciadorTarefas.application.usuario;

import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Usuario buscarPorId(UUID id);

    void remover(UUID id);

    List<Usuario> buscarTodos();

}
