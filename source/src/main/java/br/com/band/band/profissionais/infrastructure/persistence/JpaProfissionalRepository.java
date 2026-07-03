package br.com.band.band.profissionais.infrastructure.persistence;

import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.profissionais.domain.model.Profissional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaProfissionalRepository implements ProfissionalRepository {

    private final SpringDataProfissionalRepository repository;

    public JpaProfissionalRepository(SpringDataProfissionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Profissional> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Profissional> findById(UUID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void save(Profissional profissional) {
        repository.save(new ProfissionalEntity(
                profissional.getId(),
                profissional.getName(),
                profissional.getRole(),
                profissional.getDescription(),
                profissional.isDefault()
        ));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    private Profissional toDomain(ProfissionalEntity entity) {
        return new Profissional(
                entity.getId(),
                entity.getName(),
                entity.getRole(),
                entity.getDescription(),
                entity.isDefault()
        );
    }
}
