package br.com.band.band.profissionais.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfissionalNotFoundException extends RuntimeException {
    public ProfissionalNotFoundException(UUID id) {
        super("Professional not found: " + id);
    }
}
