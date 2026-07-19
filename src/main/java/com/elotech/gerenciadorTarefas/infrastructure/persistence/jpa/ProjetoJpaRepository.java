package com.elotech.gerenciadorTarefas.infrastructure.persistence.jpa;

import com.elotech.gerenciadorTarefas.infrastructure.persistence.entity.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjetoJpaRepository extends JpaRepository<ProjetoEntity, UUID> {}
