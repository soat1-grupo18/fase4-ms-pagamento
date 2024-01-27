package br.com.fiap.soat.pagamentos.gateways;

import br.com.fiap.soat.pagamentos.dynamodb.mappers.PagamentoMapper;
import br.com.fiap.soat.pagamentos.exceptions.PagamentoNaoEncontradoException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.dynamodb.repositories.PagamentoRepository;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoGatewayTest {
    @Mock
    private PagamentoRepository pagamentoRepository;
    @InjectMocks
    private PagamentoGateway pagamentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPagamento() {
        Pagamento pagamento = new Pagamento("1", "Pedido123", new BigDecimal(30), Status.PENDENTE, "2024-01-27");
        PagamentoDynamoEntity mockedEntity = PagamentoDynamoEntity.fromDomain(pagamento);

        lenient().when(pagamentoRepository.save(any(PagamentoDynamoEntity.class))).thenReturn(mockedEntity);

        Pagamento createdPagamento = pagamentoGateway.criarPagamento(pagamento);

        assertNotNull(createdPagamento);
        assertEquals(pagamento.getId(), createdPagamento.getId());
        assertEquals(pagamento.getPedidoId(), createdPagamento.getPedidoId());
        assertEquals(pagamento.getStatus(), createdPagamento.getStatus());
        assertEquals(pagamento.getDataDeCriacao(), createdPagamento.getDataDeCriacao());
    }

    @Test
    void obterPagamentoPorId_retornaSemPagamentos() {
        String pagamentoId = "1";

        lenient().when(pagamentoRepository.findById(pagamentoId)).thenReturn(Optional.empty());

        assertThrows(PagamentoNaoEncontradoException.class, () -> {
            pagamentoGateway.obterPagamentoPorId(pagamentoId);
        });
    }

    @Test
    void obterPagamentosPorStatus_retornaSemPagamentos() {
        Status status = Status.RECUSADO;
        Pagamento pagamento = new Pagamento("1", "Pedido123", new BigDecimal(30), status, "2024-01-27");
        PagamentoDynamoEntity mockedEntity = PagamentoMapper.toEntity(pagamento);

        List<PagamentoDynamoEntity> pagamentosList = Collections.singletonList(mockedEntity);

        lenient().when(pagamentoRepository.findByStatus(status)).thenReturn(pagamentosList);

        List<Pagamento> pagamentos = pagamentoGateway.obterPagamentosPorStatus(status);

        assertTrue(pagamentos.isEmpty());
    }
}
