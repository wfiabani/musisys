package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.dto.SetlistDTO;
import br.com.band.band.repertorio.application.dto.SetlistItemDTO;
import br.com.band.band.repertorio.domain.model.Music;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;

import java.util.List;
import java.util.UUID;

public class GetSetlistWithMusicsUseCase {

    private final SetlistRepository setlistRepository;
    private final MusicRepository musicRepository;

    public GetSetlistWithMusicsUseCase(SetlistRepository setlistRepository, MusicRepository musicRepository) {
        this.setlistRepository = setlistRepository;
        this.musicRepository = musicRepository;
    }

    public SetlistDTO execute(UUID id) {

        Setlist setlist = setlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setlist not found"));

        List<Music> musics = musicRepository.findBySetlistId(id);

        List<SetlistItemDTO> items = setlist.getItems()
                .stream()
                .map(item -> {
                    Music music = musics.stream()
                            .filter(m -> m.getId().equals(item.getMusicId()))
                            .findFirst()
                            .orElseThrow(() ->
                                    new RuntimeException("Music not found for setlist item")
                            );

                    return new SetlistItemDTO(
                            music.getId(),
                            music.getTitle(),
                            music.getKey(),
                            music.getAuthor(),
                            item.getPosition()
                    );
                })
                .toList();

        return new SetlistDTO(
                setlist.getId(),
                setlist.getName(),
                items
        );
    }
}