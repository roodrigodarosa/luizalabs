package com.luizalabs.agendamento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractController {

    public AbstractController() {
    }

    protected <T> ResponseEntity<T> buildNoContent() {
        return ResponseEntity.noContent().build();
    }

    protected <T> ResponseEntity<T> buildCreated(T object) {
        return ResponseEntity.status(HttpStatus.CREATED).body(object);
    }

    protected <T> ResponseEntity<T> buildOK(T object) {
        return ResponseEntity.ok(object);
    }
    protected <T> ResponseEntity<String> buildError(Exception e, int status) {
        return ResponseEntity.
                status(status).
                body(e.getMessage());
    }


}
