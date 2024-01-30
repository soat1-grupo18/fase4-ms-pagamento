package br.com.fiap.soat.pagamentos.mappers;

import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.dynamodb.mappers.PagamentoMapper;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PagamentoMapperTest {
    @Test
    void testToDomain() {
        PagamentoDynamoEntity entity = new PagamentoDynamoEntity();
        entity.setId("123");
        entity.setPedidoId("456");
        entity.setTotal(BigDecimal.valueOf(100.00));
        entity.setStatus(Status.PENDENTE);
        entity.setDataDeCriacao("2022-01-01");

        Pagamento pagamento = PagamentoMapper.toDomain(entity);

        assertEquals("123", pagamento.getId());
        assertEquals("456", pagamento.getPedidoId());
        assertEquals(BigDecimal.valueOf(100.00), pagamento.getTotal());
        assertEquals(Status.PENDENTE, pagamento.getStatus());
        assertEquals("2022-01-01", pagamento.getDataDeCriacao());
    }

    @Test
    void testToEntity() {
        Pagamento pagamento = new Pagamento("123", "456", BigDecimal.valueOf(100.00), Status.PENDENTE, "2022-01-01");

        PagamentoDynamoEntity entity = PagamentoMapper.toEntity(pagamento);

        assertEquals("123", entity.getId());
        assertEquals("456", entity.getPedidoId());
        assertEquals(BigDecimal.valueOf(100.00), entity.getTotal());
        assertEquals(Status.PENDENTE, entity.getStatus());
        assertEquals("2022-01-01", entity.getDataDeCriacao());
    }

    @Test
    void testToDomainWithMock() {
        PagamentoDynamoEntity entity = mock(PagamentoDynamoEntity.class);
        when(entity.getId()).thenReturn("123");
        when(entity.getPedidoId()).thenReturn("456");
        when(entity.getTotal()).thenReturn(BigDecimal.valueOf(100.00));
        when(entity.getStatus()).thenReturn(Status.PENDENTE);
        when(entity.getDataDeCriacao()).thenReturn("2022-01-01");

        Pagamento pagamento = PagamentoMapper.toDomain(entity);

        assertEquals("123", pagamento.getId());
        assertEquals("456", pagamento.getPedidoId());
        assertEquals(BigDecimal.valueOf(100.00), pagamento.getTotal());
        assertEquals(Status.PENDENTE, pagamento.getStatus());
        assertEquals("2022-01-01", pagamento.getDataDeCriacao());
    }

    @Test
    void testToEntityWithMock() {
        Pagamento pagamento = mock(Pagamento.class);
        when(pagamento.getId()).thenReturn("123");
        when(pagamento.getPedidoId()).thenReturn("456");
        when(pagamento.getTotal()).thenReturn(BigDecimal.valueOf(100.00));
        when(pagamento.getStatus()).thenReturn(Status.PENDENTE);
        when(pagamento.getDataDeCriacao()).thenReturn("2022-01-01");

        PagamentoDynamoEntity entity = PagamentoMapper.toEntity(pagamento);

        assertEquals("123", entity.getId());
        assertEquals("456", entity.getPedidoId());
        assertEquals(BigDecimal.valueOf(100.00), entity.getTotal());
        assertEquals(Status.PENDENTE, entity.getStatus());
        assertEquals("2022-01-01", entity.getDataDeCriacao());
    }
}
