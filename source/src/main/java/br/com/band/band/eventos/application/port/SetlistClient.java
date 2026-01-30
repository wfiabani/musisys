package br.com.band.band.eventos.application.port;

import br.com.band.band.eventos.application.dto.SetlistDto;

import java.util.Optional;
import java.util.UUID;

public interface SetlistClient {

    Optional<SetlistDto> findById(UUID setlistId);

}
