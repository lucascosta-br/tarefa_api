package com.github.lucascostabr.tarefa_api.controller;

import com.github.lucascostabr.tarefa_api.controller.docs.TarefaControllerDocs;
import com.github.lucascostabr.tarefa_api.dto.TarefaDTO;
import com.github.lucascostabr.tarefa_api.service.TarefaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController implements TarefaControllerDocs {

    private final TarefaService tarefaService;
    private final Logger logger = LoggerFactory.getLogger(TarefaController.class);

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    @Override
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO dto) {
        logger.info("Criado uma tarefa com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvar(dto));
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @Override
    public List<TarefaDTO> listar() {
        logger.info("Listada as tarefas com sucesso");

        return tarefaService.listar();
    }

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<TarefaDTO> listarPorId(@PathVariable Long id) {
        logger.info("Listando uma tarefa com sucesso");

        return ResponseEntity.ok(tarefaService.listarPorId(id));
    }

    @PutMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    @Override
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @RequestBody TarefaDTO dto) {
        logger.info("Atualizando uma tarefa com sucesso");

        return ResponseEntity.ok(tarefaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        logger.info("Excluindo uma tarefa com sucesso");

        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
