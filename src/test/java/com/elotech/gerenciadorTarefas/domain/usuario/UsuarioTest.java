package com.elotech.gerenciadorTarefas.domain.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsuarioTest {

    @Test
    void deveCriarUsuarioValido() {

        Usuario usuario = new Usuario("Silva", "silva@gmail.com");

        assertNotNull(usuario);
        assertEquals("Silva", usuario.getNome());
        assertEquals("silva@gmail.com", usuario.getEmail());
    }
}
