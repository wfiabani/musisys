package br.com.band.band.repertorio.application;

import br.com.band.band.repertorio.application.dto.SetlistDTO;
import br.com.band.band.repertorio.application.dto.SetlistSummaryDTO;
import br.com.band.band.repertorio.application.usecase.*;
import br.com.band.band.repertorio.domain.model.Music;

import java.util.List;
import java.util.UUID;

public class RepertorioService {

    private final ListAllMusicsUseCase listAllMusicsUseCase;
    private final CreateMusicUseCase createMusicUseCase;
    private final UpdateMusicUseCase updateMusicUseCase;
    private final DeleteMusicUseCase deleteMusicUseCase;
    private final ListAllSetlistsUseCase listAllSetlistsUseCase;
    private final GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase;
    private final CreateSetlistUseCase createSetlistUseCase;
    private final RemoveSetlistUseCase removeSetlistUseCase;
    private final AddMusicToSetlistUseCase addMusicToSetlistUseCase;
    private final RemoveMusicFromSetlistUseCase removeMusicFromSetlistUseCase;
    private final ReorderSetlistUseCase reorderSetlistUseCase;

    public RepertorioService(
            ListAllMusicsUseCase listAllMusicsUseCase,
            CreateMusicUseCase createMusicUseCase,
            UpdateMusicUseCase updateMusicUseCase,
            DeleteMusicUseCase deleteMusicUseCase,
            ListAllSetlistsUseCase listAllSetlistsUseCase,
            GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase,
            CreateSetlistUseCase createSetlistUseCase,
            RemoveSetlistUseCase removeSetlistUseCase,
            AddMusicToSetlistUseCase addMusicToSetlistUseCase,
            RemoveMusicFromSetlistUseCase removeMusicFromSetlistUseCase,
            ReorderSetlistUseCase reorderSetlistUseCase
    ) {
        this.listAllMusicsUseCase = listAllMusicsUseCase;
        this.createMusicUseCase = createMusicUseCase;
        this.updateMusicUseCase = updateMusicUseCase;
        this.deleteMusicUseCase = deleteMusicUseCase;
        this.listAllSetlistsUseCase = listAllSetlistsUseCase;
        this.getSetlistWithMusicsUseCase = getSetlistWithMusicsUseCase;
        this.createSetlistUseCase = createSetlistUseCase;
        this.removeSetlistUseCase = removeSetlistUseCase;
        this.addMusicToSetlistUseCase = addMusicToSetlistUseCase;
        this.removeMusicFromSetlistUseCase = removeMusicFromSetlistUseCase;
        this.reorderSetlistUseCase = reorderSetlistUseCase;
    }

    public List<Music> listAllMusics() {
        return listAllMusicsUseCase.execute();
    }

    public UUID createMusic(String title, String author, String key) {
        return createMusicUseCase.execute(title, author, key);
    }

    public void updateMusic(UUID id, String title, String author, String key) {
        updateMusicUseCase.execute(id, title, author, key);
    }

    public void deleteMusic(UUID musicId) {
        deleteMusicUseCase.execute(musicId);
    }

    public List<SetlistSummaryDTO> listAllSetlists() {
        return listAllSetlistsUseCase.execute();
    }

    public SetlistDTO getSetlistWithMusics(UUID setlistId) {
        return getSetlistWithMusicsUseCase.execute(setlistId);
    }

    public UUID createSetlist(String name) {
        return createSetlistUseCase.execute(name);
    }

    public void removeSetlist(UUID setlistId) {
        removeSetlistUseCase.execute(setlistId);
    }

    public void addMusicToSetlist(UUID setlistId, UUID musicId) {
        addMusicToSetlistUseCase.execute(setlistId, musicId);
    }

    public void removeMusicFromSetlist(UUID setlistId, UUID musicId) {
        removeMusicFromSetlistUseCase.execute(setlistId, musicId);
    }

    public void reorderSetlist(UUID setlistId, UUID musicId, int newPosition) {
        reorderSetlistUseCase.execute(setlistId, musicId, newPosition);
    }
}
