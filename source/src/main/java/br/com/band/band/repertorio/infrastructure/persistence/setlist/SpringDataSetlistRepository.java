package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataSetlistRepository
        extends JpaRepository<SetlistEntity, UUID> {

    @Query("select distinct s from SetlistEntity s left join fetch s.items")
    List<SetlistEntity> findAllWithItems();
}