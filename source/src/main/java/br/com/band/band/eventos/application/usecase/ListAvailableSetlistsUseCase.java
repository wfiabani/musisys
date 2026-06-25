package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.dto.SetlistSummaryDto;
import br.com.band.band.eventos.application.port.SetlistClient;

import java.util.List;

public class ListAvailableSetlistsUseCase {

    private final SetlistClient setlistClient;

    public ListAvailableSetlistsUseCase(SetlistClient setlistClient) {
        this.setlistClient = setlistClient;
    }

    public List<SetlistSummaryDto> execute() {
        return setlistClient.findAll();
    }
}
