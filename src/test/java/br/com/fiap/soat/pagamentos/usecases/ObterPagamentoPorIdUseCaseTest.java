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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObterPagamentoPorIdUseCaseTest {
    @Mock
    private PagamentosGatewayPort pagamentoGateway;
    private CriarPagamentoUseCase criarPagamentoUseCase;
    private ObterPagamentoPorIdUseCase obterPagamentoPorIdUseCase;

    @BeforeEach
    void initUseCase() {
        obterPagamentoPorIdUseCase = new ObterPagamentoPorIdUseCase(pagamentoGateway);
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

        when(obterPagamentoPorIdUseCase.execute(eq(pagamento.getId()))).thenReturn(Optional.of(pagamento));

        Optional<Pagamento> optionalPagamento = obterPagamentoPorIdUseCase.execute(pagamento.getId());
        verify(pagamentoGateway).obterPagamentoPorId(eq(pagamento.getId()));


        if (optionalPagamento.isPresent()) {
            Pagamento pagamentoEncontrado = optionalPagamento.get();
            assertNotNull(pagamentoEncontrado);
            assertNotNull(pagamentoEncontrado.getPedidoId());
            assertEquals(Status.APROVADO, pagamentoEncontrado.getStatus());
            assertEquals(new BigDecimal("70.50"), pagamentoEncontrado.getTotal());
            assertEquals(pagamento, pagamentoEncontrado);
        } else {
            fail("Pagamento not found for ID: " + pagamento.getId());
        }
    }
}




