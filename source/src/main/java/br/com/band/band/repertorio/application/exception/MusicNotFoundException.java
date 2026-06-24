package br.com.band.band.repertorio.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MusicNotFoundException extends RuntimeException {
    public MusicNotFoundException(UUID id) {
        super("Music not found: " + id);
    }
}
