package com.elotech.gerenciadorTarefas.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projetos")
@Getter
@Setter
public class ProjetoEntity {

    @Id
    private UUID id;

    private String nome;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarefaEntity> tarefas = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembroProjetoEntity> membros = new ArrayList<>();

    protected ProjetoEntity() {
        //NA
    }

    public ProjetoEntity(final UUID id, final String nome) {
        this.id = id;
        this.nome = nome;
        this.tarefas = new ArrayList<>();
        this.membros = new ArrayList<>();
    }

    public void adicionarTarefa(final TarefaEntity tarefa) {
        tarefas.add(tarefa);
        tarefa.setProjeto(this);
    }


    public void adicionarMembro(final MembroProjetoEntity membro) {
        membros.add(membro);
        membro.setProjeto(this);
    }

}
