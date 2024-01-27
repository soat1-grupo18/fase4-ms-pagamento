package br.com.fiap.soat.pagamentos.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CriarPagamentoUseCaseTest {
    @Mock
    private PagamentosGatewayPort pagamentoGateway;
    private CriarPagamentoUseCase criarPagamentoUseCase;

    @BeforeEach
    void initUseCase() {
        criarPagamentoUseCase = new CriarPagamentoUseCase(pagamentoGateway);
    }

    @Test
    void execute() {
        var pagamento = new Pagamento("Pedido123", new BigDecimal(70.50), Status.APROVADO, String.valueOf(LocalDateTime.now()));
        ArgumentCaptor<Pagamento> pagamentoCaptor = ArgumentCaptor.forClass(Pagamento.class);

        when(pagamentoGateway.criarPagamento(pagamentoCaptor.capture())).thenReturn(pagamento);

        var novoPagamento = criarPagamentoUseCase.execute(pagamento);

        verify(pagamentoGateway).criarPagamento(pagamento);

        Pagamento capturedPagamento = pagamentoCaptor.getValue();
        assertNotNull(capturedPagamento);
        assertNotNull(capturedPagamento.getPedidoId());

        assertEquals(Status.APROVADO, capturedPagamento.getStatus());
        assertEquals(new BigDecimal(70.50), capturedPagamento.getTotal());
        assertEquals(pagamento, novoPagamento);
    }
}




