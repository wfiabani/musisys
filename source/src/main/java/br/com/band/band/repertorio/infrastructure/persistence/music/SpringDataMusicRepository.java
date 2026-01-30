package br.com.band.band.repertorio.infrastructure.persistence.music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataMusicRepository
        extends JpaRepository<MusicEntity, UUID> {

    @Query("""
        select m
        from MusicEntity m
        join SetlistItemEntity si
            on si.musicId = m.id
        where si.setlist.id = :setlistId
        order by si.position
    """)
    List<MusicEntity> findBySetlistId(@Param("setlistId") UUID setlistId);
}
