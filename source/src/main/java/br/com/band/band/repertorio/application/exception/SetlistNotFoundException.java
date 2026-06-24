package br.com.band.band.repertorio.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SetlistNotFoundException extends RuntimeException {
    public SetlistNotFoundException(UUID id) {
        super("Setlist not found: " + id);
    }
}
