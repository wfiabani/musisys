package br.com.band.band.repertorio.application;

import br.com.band.band.repertorio.application.dto.SetlistDTO;
import br.com.band.band.repertorio.application.usecase.GetSetlistWithMusicsUseCase;
import br.com.band.band.repertorio.application.usecase.ListAllMusicsUseCase;
import br.com.band.band.repertorio.application.usecase.RemoveSetlistUseCase;
import br.com.band.band.repertorio.domain.model.Music;

import java.util.List;
import java.util.UUID;

public class RepertorioService {

    private final ListAllMusicsUseCase listAllMusicsUseCase;
    private final GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase;
    private final RemoveSetlistUseCase removeSetlistUseCase;

    public RepertorioService(ListAllMusicsUseCase listAllMusicsUseCase, GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase, RemoveSetlistUseCase removeSetlistUseCase) {
        this.listAllMusicsUseCase = listAllMusicsUseCase;
        this.getSetlistWithMusicsUseCase = getSetlistWithMusicsUseCase;
        this.removeSetlistUseCase = removeSetlistUseCase;
    }

    public List<Music> listAllMusics() {
        return listAllMusicsUseCase.execute();
    }

    public SetlistDTO getSetlistWithMusics(UUID setlistId) {
        return getSetlistWithMusicsUseCase.execute(setlistId);
    }

    public void removeSetlist(UUID setlistId) {
        removeSetlistUseCase.execute(setlistId);
    }

}
