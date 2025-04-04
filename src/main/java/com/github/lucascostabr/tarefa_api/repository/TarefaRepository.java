package com.github.lucascostabr.tarefa_api.repository;

import com.github.lucascostabr.tarefa_api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
