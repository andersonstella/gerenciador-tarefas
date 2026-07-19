package com.elotech.gerenciadorTarefas.infrastructure.persistence.repository;

import com.elotech.gerenciadorTarefas.application.usuario.UsuarioRepository;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.UsuarioEntity;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.jpa.UsuarioJpaRepository;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper.UsuarioMapper;

import java.util.UUID;

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

        final UsuarioEntity entity = usuarioJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UsuarioMapper.toDomain(entity);
    }

    @Override
    public void remover(final UUID id) {

        if (!usuarioJpaRepository.existsById(id)) {
            throw new RegraNegocioException("Usuário não encontrado");
        }
        usuarioJpaRepository.deleteById(id);
    }

}
