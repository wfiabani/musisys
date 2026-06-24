package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SpringDataSetlistItemRepository extends JpaRepository<SetlistItemEntity, UUID> {

    @Modifying(clearAutomatically = true)
    @Query("delete from SetlistItemEntity si where si.setlist.id = :setlistId")
    void deleteBySetlistId(@Param("setlistId") UUID setlistId);
}
