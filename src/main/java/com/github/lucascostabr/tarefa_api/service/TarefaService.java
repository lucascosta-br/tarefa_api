package com.github.lucascostabr.tarefa_api.service;

import com.github.lucascostabr.tarefa_api.controller.TarefaController;
import com.github.lucascostabr.tarefa_api.dto.TarefaDTO;
import com.github.lucascostabr.tarefa_api.exception.TarefaNaoEncontradaException;
import com.github.lucascostabr.tarefa_api.model.Tarefa;
import com.github.lucascostabr.tarefa_api.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaDTO salvar(TarefaDTO dto) {
        Tarefa tarefa = toEntity(dto);
        addHateoasLinks(dto);
        return toDTO(tarefaRepository.save(tarefa));
    }

    public List<TarefaDTO> listar() {
        List<TarefaDTO> dtoLista = tarefaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
        dtoLista.forEach(this::addHateoasLinks);
        return dtoLista;
    }

    public TarefaDTO listarPorId(Long id) {
        TarefaDTO dto = tarefaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() ->
                        new TarefaNaoEncontradaException("Tarefa de ID " + id + " não encontrado"));
        addHateoasLinks(dto);
        return dto;
    }

    public TarefaDTO atualizar(Long id, TarefaDTO dto) {
        Tarefa tarefaExistente = tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa de ID " + id + " não encontrado"));

        tarefaExistente.setTitulo(dto.getTitulo());
        tarefaExistente.setDescricao(dto.getDescricao());
        tarefaExistente.setCompleta(dto.getCompleta());

        addHateoasLinks(dto);
        return toDTO(tarefaRepository.save(tarefaExistente));
    }

    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNaoEncontradaException("Tarefa de ID " + id + " não encontrado");
        }

        tarefaRepository.deleteById(id);
    }

    private TarefaDTO toDTO(Tarefa tarefa) {
        return new TarefaDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getCompleta());
    }

    private Tarefa toEntity(TarefaDTO dto) {
        return new Tarefa(dto.getId(), dto.getTitulo(), dto.getDescricao(), dto.getCompleta());
    }

    private void addHateoasLinks(TarefaDTO dto) {
        dto.add(linkTo(methodOn(TarefaController.class).listar()).withRel("listar").withType("GET"));
        dto.add(linkTo(methodOn(TarefaController.class).listarPorId(dto.getId())).withRel("listarPorId").withType("GET"));
        dto.add(linkTo(methodOn(TarefaController.class).salvar(dto)).withRel("salvar").withType("POST"));
        dto.add(linkTo(methodOn(TarefaController.class).atualizar(dto.getId(), dto)).withRel("atualizar").withType("PUT"));
        dto.add(linkTo(methodOn(TarefaController.class).deletar(dto.getId())).withRel("deletar").withType("DELETE"));
    }

}
