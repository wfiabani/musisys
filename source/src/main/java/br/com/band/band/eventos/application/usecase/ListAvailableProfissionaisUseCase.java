package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.dto.ProfissionalSummaryDto;
import br.com.band.band.eventos.application.port.ProfissionalClient;

import java.util.List;

public class ListAvailableProfissionaisUseCase {

    private final ProfissionalClient profissionalClient;

    public ListAvailableProfissionaisUseCase(ProfissionalClient profissionalClient) {
        this.profissionalClient = profissionalClient;
    }

    public List<ProfissionalSummaryDto> execute() {
        return profissionalClient.findAll();
    }
}
