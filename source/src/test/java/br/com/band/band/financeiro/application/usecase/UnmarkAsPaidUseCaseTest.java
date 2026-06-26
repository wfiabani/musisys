package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.exception.TransactionNotFoundException;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.domain.model.Transaction;
import br.com.band.band.financeiro.domain.model.TransactionStatus;
import br.com.band.band.financeiro.domain.model.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnmarkAsPaidUseCaseTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private UnmarkAsPaidUseCase useCase;

    @Test
    void execute_transacaoNaoExiste_lancaTransactionNotFoundException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(TransactionNotFoundException.class);

        verify(repository, never()).save(any());
    }

    @Test
    void execute_transacaoPaga_reverteStatusParaPendente() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(transacaoPaga(id)));

        useCase.execute(id);

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getStatus()).isEqualTo(TransactionStatus.PENDING);
    }

    @Test
    void execute_transacaoPaga_zeraDadaDePagamento() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(transacaoPaga(id)));

        useCase.execute(id);

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getPaymentDate()).isNull();
    }

    @Test
    void execute_transacaoPaga_persisteAlteracao() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(transacaoPaga(id)));

        useCase.execute(id);

        verify(repository).save(any(Transaction.class));
    }

    private Transaction transacaoPaga(UUID id) {
        var t = new Transaction(
                id,
                TransactionType.EXPENSE,
                "Aluguel do espaço",
                new BigDecimal("500.00"),
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 10),
                TransactionStatus.PAID,
                "Infraestrutura",
                null
        );
        return t;
    }
}
