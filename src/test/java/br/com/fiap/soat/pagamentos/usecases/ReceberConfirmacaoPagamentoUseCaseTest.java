package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.gateways.ProducaoGatewayPort;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceberConfirmacaoPagamentoUseCaseTest {

    @Mock
    private PagamentosGatewayPort pagamentoGateway;

    @Mock
    private ProducaoGatewayPort producaoGateway;

    private ReceberConfirmacaoPagamentoUseCase receberConfirmacaoPagamentoUseCase;

    @BeforeEach
    void initUseCase() {
        receberConfirmacaoPagamentoUseCase = new ReceberConfirmacaoPagamentoUseCase(pagamentoGateway, producaoGateway);
    }

    @Test
    void execute() {
        var pagamentoId = UUID.randomUUID().toString();
        var comando = new ComandoDeConfirmacaoDePagamento("payment.created", pagamentoId);
        var pagamento = new Pagamento(pagamentoId, UUID.randomUUID().toString(), BigDecimal.valueOf(15), Status.PENDENTE, LocalDateTime.now().toString());
        when(pagamentoGateway.obterPagamentoPorId(pagamentoId)).thenReturn(Optional.of(pagamento));
        var result = receberConfirmacaoPagamentoUseCase.execute(comando);
        assertEquals(result, String.format("Pagamento %s confirmado", pagamentoId));
        verify(pagamentoGateway, times(1)).obterPagamentoPorId(pagamentoId);
    }
}