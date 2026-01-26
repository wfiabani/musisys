package br.com.band.band.repertorio.application;

import br.com.band.band.repertorio.application.usecase.ListAllMusicsUseCase;
import br.com.band.band.repertorio.domain.music.model.Music;

import java.util.List;

public class RepertorioService {

    private final ListAllMusicsUseCase listAllMusicsUseCase;

    public RepertorioService(ListAllMusicsUseCase listAllMusicsUseCase) {
        this.listAllMusicsUseCase = listAllMusicsUseCase;
    }

    public List<Music> listAllMusics() {
        return listAllMusicsUseCase.execute();
    }

}
