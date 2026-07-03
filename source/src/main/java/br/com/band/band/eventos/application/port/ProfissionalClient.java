package br.com.band.band.eventos.application.port;

import br.com.band.band.eventos.application.dto.ProfissionalSummaryDto;

import java.util.List;

public interface ProfissionalClient {

    List<ProfissionalSummaryDto> findAll();
}
