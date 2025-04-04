package com.github.lucascostabr.tarefa_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO extends RepresentationModel<TarefaDTO> {
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean completa;
}
