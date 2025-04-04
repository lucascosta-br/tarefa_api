package com.github.lucascostabr.tarefa_api.exception;

public class TarefaNaoEncontradaException extends RuntimeException {
    public TarefaNaoEncontradaException(String message) {
        super(message);
    }
}
