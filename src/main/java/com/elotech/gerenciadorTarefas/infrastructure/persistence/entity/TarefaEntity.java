package com.elotech.gerenciadorTarefas.infrastructure.persistence.entity;

import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.StatusTarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
public class TarefaEntity {

    @Id
    private UUID id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @Enumerated(EnumType.STRING)
    private PrioridadeTarefa prioridade;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private LocalDate prazo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private ProjetoEntity projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity responsavel;

    protected TarefaEntity() {
        //NA
    }

    public TarefaEntity(final UUID id, final String titulo, final String descricao, final StatusTarefa status,
                        final PrioridadeTarefa prioridade, final LocalDateTime criadoEm,
                        final LocalDateTime atualizadoEm, final LocalDate prazo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.prazo = prazo;
    }

}