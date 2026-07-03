package br.com.band.band.eventos.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SpringDataEventProfessionalRepository extends JpaRepository<EventProfessionalEntity, UUID> {

    @Modifying(clearAutomatically = true)
    @Query("delete from EventProfessionalEntity ep where ep.event.id = :eventId")
    void deleteByEventId(@Param("eventId") UUID eventId);
}
