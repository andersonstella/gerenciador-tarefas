package com.elotech.gerenciadorTarefas.infrastructure.persistence.repository;

import com.elotech.gerenciadorTarefas.application.projeto.ProjetoRepository;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import com.elotech.gerenciadorTarefas.infrastructure.exception.RegistroNaoEncontradoException;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.ProjetoEntity;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.jpa.ProjetoJpaRepository;
import com.elotech.gerenciadorTarefas.infrastructure.persistence.mapper.ProjetoMapper;

import java.util.UUID;

public class ProjetoRepositoryImpl implements ProjetoRepository {

    private final ProjetoJpaRepository projetoJpaRepository;


    public ProjetoRepositoryImpl(final ProjetoJpaRepository projetoJpaRepository) {
        this.projetoJpaRepository = projetoJpaRepository;
    }

    @Override
    public Projeto salvar(final Projeto projeto) {

        final ProjetoEntity entity = ProjetoMapper.toEntity(projeto);
        final ProjetoEntity salvo = projetoJpaRepository.save(entity);

        return ProjetoMapper.toDomain(salvo);
    }

    @Override
    public Projeto buscarPorId(final UUID id) {

        final ProjetoEntity entity = projetoJpaRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Projeto não encontrado"));

        return ProjetoMapper.toDomain(entity);
    }

    @Override
    public void remover(final UUID id) {

        if (!projetoJpaRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException("Usuário não encontrado");
        }
        projetoJpaRepository.deleteById(id);
    }
}
