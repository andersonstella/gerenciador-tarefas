package com.elotech.gerenciadorTarefas.infrastructure.persistence.repository;

import com.elotech.gerenciadorTarefas.application.usuario.UsuarioRepository;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import com.elotech.gerenciadorTarefas.infrastructure.exception.RegistroNaoEncontradoException;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.UsuarioEntity;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.jpa.UsuarioJpaRepository;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper.UsuarioMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryImpl(final UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario salvar(final Usuario usuario) {

        final UsuarioEntity entity = UsuarioMapper.toEntity(usuario);
        final UsuarioEntity salvo = usuarioJpaRepository.save(entity);

        return UsuarioMapper.toDomain(salvo);
    }

    @Override
    public Usuario buscarPorId(final UUID id) {

        final UsuarioEntity entity = usuarioJpaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado"));

        return UsuarioMapper.toDomain(entity);
    }

    @Override
    public void remover(final UUID id) {

        if (!usuarioJpaRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException("Usuário não encontrado");
        }
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public List<Usuario> buscarTodos() {

        final List<UsuarioEntity> usuariosEntity = usuarioJpaRepository.findAll();
        final List<Usuario> usuarios = new ArrayList<>();

        for (UsuarioEntity entity : usuariosEntity) {
            usuarios.add(toDomain(entity));
        }

        return usuarios;
    }

    @Override
    public Usuario buscarPorEmail(final String email) {

        UsuarioEntity entity = usuarioJpaRepository.findByEmail(email)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado"));

        return UsuarioMapper.toDomain(entity);
    }

    private Usuario toDomain(final UsuarioEntity entity) {

        return new Usuario(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenha()
        );
    }

}
