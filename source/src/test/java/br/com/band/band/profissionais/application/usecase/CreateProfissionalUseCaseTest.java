package br.com.band.band.profissionais.application.usecase;

import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.profissionais.domain.model.Profissional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateProfissionalUseCaseTest {

    @Mock
    private ProfissionalRepository repository;

    @InjectMocks
    private CreateProfissionalUseCase useCase;

    @Test
    void execute_criaEPersisteProfissionalComDadosInformados() {
        UUID id = useCase.execute("João Silva", "Vocalista", "Vocalista principal", true);

        ArgumentCaptor<Profissional> captor = ArgumentCaptor.forClass(Profissional.class);
        verify(repository).save(captor.capture());

        Profissional saved = captor.getValue();
        assertThat(saved.getId()).isEqualTo(id);
        assertThat(saved.getName()).isEqualTo("João Silva");
        assertThat(saved.getRole()).isEqualTo("Vocalista");
        assertThat(saved.getDescription()).isEqualTo("Vocalista principal");
        assertThat(saved.isDefault()).isTrue();
    }
}
