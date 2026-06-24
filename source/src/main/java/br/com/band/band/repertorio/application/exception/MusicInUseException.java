package br.com.band.band.repertorio.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class MusicInUseException extends RuntimeException {
    public MusicInUseException(UUID id) {
        super("Music " + id + " belongs to one or more setlists and cannot be deleted");
    }
}
