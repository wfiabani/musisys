package br.com.band.band.profissionais.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProfissionalInUseException extends RuntimeException {
    public ProfissionalInUseException(UUID id) {
        super("Professional " + id + " is linked to an event that already occurred and cannot be deleted");
    }
}
