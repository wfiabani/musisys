package br.com.band.band.eventos.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataEventRepository
        extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findBySetlistId(UUID id);

    List<EventEntity> findByProfessionals_ProfessionalId(UUID professionalId);

    @Query("select distinct e from EventEntity e left join fetch e.professionals")
    List<EventEntity> findAllWithProfessionals();
}
