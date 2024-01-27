package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObterPagamentosPorStatusUseCaseTest {
    @Mock
    private PagamentosGatewayPort pagamentoGateway;
    private CriarPagamentoUseCase criarPagamentoUseCase;
    private ObterPagamentosPorStatusUseCase obterPagamentosPorStatusUseCase;

    @BeforeEach
    void initUseCase() {
        obterPagamentosPorStatusUseCase = new ObterPagamentosPorStatusUseCase(pagamentoGateway);
        criarPagamentoUseCase = new CriarPagamentoUseCase(pagamentoGateway);
    }

    @Test
    void execute() {
        var pagamento = new Pagamento(
                "PedidoId123",
                "Pedido123",
                new BigDecimal("70.50"),
                Status.APROVADO,
                String.valueOf(LocalDateTime.now())
        );


        when(pagamentoGateway.criarPagamento(eq(pagamento))).thenReturn(pagamento);
        criarPagamentoUseCase.execute(pagamento);
        verify(pagamentoGateway).criarPagamento(eq(pagamento));


        when(obterPagamentosPorStatusUseCase.execute(eq(pagamento.getStatus()))).thenReturn(List.of((pagamento)));

        List<Pagamento> pagamentosList = obterPagamentosPorStatusUseCase.execute(pagamento.getStatus());

        verify(pagamentoGateway).obterPagamentosPorStatus(eq(pagamento.getStatus()));

        for (Pagamento pagamentoEncontrado : pagamentosList) {
            assertNotNull(pagamentoEncontrado);
            assertNotNull(pagamentoEncontrado.getPedidoId());
            assertEquals(Status.APROVADO, pagamentoEncontrado.getStatus());
            assertEquals(new BigDecimal("70.50"), pagamentoEncontrado.getTotal());
            assertEquals(pagamento, pagamentoEncontrado);
        }
    }
}


