package br.com.band.band.eventos.application.port;

import br.com.band.band.eventos.application.dto.SetlistDto;
import br.com.band.band.eventos.application.dto.SetlistSummaryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SetlistClient {

    List<SetlistSummaryDto> findAll();

    Optional<SetlistDto> findById(UUID setlistId);
}
