package com.elotech.gerenciadorTarefas.infrastructure.persistence.entity;

import com.elotech.gerenciadorTarefas.domain.projeto.PapelMembro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "membros_projeto")
@Setter
@Getter
public class MembroProjetoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private ProjetoEntity projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    private PapelMembro papel;

    protected MembroProjetoEntity() {
        //NA
    }

    public MembroProjetoEntity(final UUID id, final PapelMembro papel) {
        this.id = id;
        this.papel = papel;
    }

}