package br.com.fiap.soat.pagamentos.dynamodb.mappers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PagamentoMapperTest {
    @Test
    void toDomain() {
        PagamentoDynamoEntity entity = new PagamentoDynamoEntity();
        entity.setId("1");
        entity.setPedidoId("Pedido123");
        entity.setTotal(new BigDecimal("70.50"));
        entity.setStatus(Status.APROVADO);
        entity.setDataDeCriacao("2022-01-28T10:30:00Z");

        Pagamento pagamento = PagamentoMapper.toDomain(entity);

        assertEquals("1", pagamento.getId());
        assertEquals("Pedido123", pagamento.getPedidoId());
        assertEquals(new BigDecimal("70.50"), pagamento.getTotal());
        assertEquals(Status.APROVADO, pagamento.getStatus());
        assertEquals("2022-01-28T10:30:00Z", pagamento.getDataDeCriacao());
    }

    @Test
    void toEntity() {
        Pagamento pagamento = new Pagamento(
                "1",
                "Pedido123",
                new BigDecimal("70.50"),
                Status.APROVADO,
                "2022-01-28T10:30:00Z"
        );

        PagamentoDynamoEntity entity = PagamentoMapper.toEntity(pagamento);

        assertEquals("1", entity.getId());
        assertEquals("Pedido123", entity.getPedidoId());
        assertEquals(new BigDecimal("70.50"), entity.getTotal());
        assertEquals(Status.APROVADO, entity.getStatus());
        assertEquals("2022-01-28T10:30:00Z", entity.getDataDeCriacao());
    }
}
