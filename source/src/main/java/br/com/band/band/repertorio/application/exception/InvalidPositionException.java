package br.com.band.band.repertorio.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(int position, int max) {
        super("Posição inválida: " + position + ". Deve estar entre 1 e " + max + ".");
    }
}
