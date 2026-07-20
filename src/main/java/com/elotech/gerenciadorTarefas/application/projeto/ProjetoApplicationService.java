package com.elotech.gerenciadorTarefas.application.projeto;

import com.elotech.gerenciadorTarefas.api.dto.projeto.RelatorioProjetoResponseDTO;
import com.elotech.gerenciadorTarefas.application.usuario.UsuarioRepository;
import com.elotech.gerenciadorTarefas.domain.exception.RegraNegocioException;
import com.elotech.gerenciadorTarefas.domain.projeto.PapelMembro;
import com.elotech.gerenciadorTarefas.domain.projeto.Projeto;
import com.elotech.gerenciadorTarefas.domain.tarefa.PrioridadeTarefa;
import com.elotech.gerenciadorTarefas.domain.tarefa.StatusTarefa;
import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ProjetoApplicationService {

    private final ProjetoRepository projetoRepository;

    private final UsuarioRepository usuarioRepository;

    /**
     * Construtor
     *
     * @param projetoRepository
     * @param usuarioRepository
     */
    public ProjetoApplicationService(final ProjetoRepository projetoRepository,
            final UsuarioRepository usuarioRepository) {
        this.projetoRepository = projetoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Metodo para criar um projeto
     *
     * @param nome
     * @return
     */
    public Projeto criarProjeto(final String nome) {

        final Projeto projeto = new Projeto(nome);
        return projetoRepository.salvar(projeto);
    }

    /**
     * Metodo que adiciona mebro ao projeto
     *
     * @param projetoId
     * @param usuarioId
     * @param papelMembro
     * @return
     */
    public Projeto adicionarMembro(final UUID projetoId, final UUID usuarioId, final PapelMembro papelMembro) {

        final Projeto projeto = buscarPorId(projetoId);
        final Usuario usuario = buscarUsuarioPorId(usuarioId);

        projeto.adicionarMembro(usuario, papelMembro);

        return projetoRepository.salvar(projeto);
    }

    public Projeto removerMembro(final UUID projetoId, final UUID usuarioId) {

        final Projeto projeto = buscarPorId(projetoId);
        projeto.removerMembro(usuarioId);

        return projetoRepository.salvar(projeto);

    }

    public Projeto buscarPorId(final UUID projetoId) {

        if (projetoId == null) {
            throw new RegraNegocioException("Id do projeto é obrigatório.");
        }

        return projetoRepository.buscarPorId(projetoId);
    }

    public RelatorioProjetoResponseDTO gerarRelatorio(final UUID projetoId) {

        final Projeto projeto = buscarPorId(projetoId);

        final Map<String, Long> byStatus = contarPorStatus(projeto);
        final Map<String, Long> byPriority = contarPorPrioridade(projeto);

        return new RelatorioProjetoResponseDTO(byStatus, byPriority);
    }

    private Usuario buscarUsuarioPorId(final UUID usuarioId) {

        if (usuarioId == null) {
            throw new RegraNegocioException("Id do usuário é obrigatório.");
        }

        return usuarioRepository.buscarPorId(usuarioId);
    }

    private Map<String, Long> contarPorStatus(final Projeto projeto) {

        final Map<String, Long> status = new LinkedHashMap<>();

        for (StatusTarefa value : StatusTarefa.values()) {
            status.put(value.name(), 0L);
        }

        projeto.getTarefas().forEach(tarefa ->
                status.merge(
                        tarefa.getStatus().name(),
                        1L,
                        Long::sum));

        return status;
    }

    private Map<String, Long> contarPorPrioridade(final Projeto projeto) {

        final Map<String, Long> prioridade = new LinkedHashMap<>();

        for (PrioridadeTarefa value : PrioridadeTarefa.values()) {
            prioridade.put(value.name(), 0L);
        }

        projeto.getTarefas().forEach(tarefa ->
                prioridade.merge(
                        tarefa.getPrioridade().name(),
                        1L,
                        Long::sum));

        return prioridade;
    }

}
