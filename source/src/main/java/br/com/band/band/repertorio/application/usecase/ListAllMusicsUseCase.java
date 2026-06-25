package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.dto.MusicDTO;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;

import java.util.List;

public class ListAllMusicsUseCase {

    private final MusicRepository musicRepository;

    public ListAllMusicsUseCase(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<MusicDTO> execute() {
        return musicRepository.findAll().stream()
                .map(m -> new MusicDTO(m.getId(), m.getTitle(), m.getKey(), m.getAuthor()))
                .toList();
    }
}
