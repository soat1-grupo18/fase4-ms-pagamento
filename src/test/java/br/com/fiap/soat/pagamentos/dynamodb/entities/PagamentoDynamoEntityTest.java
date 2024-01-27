package br.com.fiap.soat.pagamentos.dynamodb.entities;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PagamentoDynamoEntityTest {
    @Test
    void fromDomain() {
        Pagamento pagamento = new Pagamento(
                "1",
                "Pedido123",
                new BigDecimal("70.50"),
                Status.APROVADO,
                "2022-01-28T10:30:00Z"
        );

        PagamentoDynamoEntity dynamoEntity = PagamentoDynamoEntity.fromDomain(pagamento);

        assertEquals("1", dynamoEntity.getId());
        assertEquals("Pedido123", dynamoEntity.getPedidoId());
        assertEquals(new BigDecimal("70.50"), dynamoEntity.getTotal());
        assertEquals(Status.APROVADO, dynamoEntity.getStatus());
        assertEquals("2022-01-28T10:30:00Z", dynamoEntity.getDataDeCriacao());
    }

    @Test
    void toDomain() {
        PagamentoDynamoEntity dynamoEntity = new PagamentoDynamoEntity();
        dynamoEntity.setId("1");
        dynamoEntity.setPedidoId("Pedido123");
        dynamoEntity.setTotal(new BigDecimal("70.50"));
        dynamoEntity.setStatus(Status.APROVADO);
        dynamoEntity.setDataDeCriacao("2022-01-28T10:30:00Z");

        Pagamento pagamento = dynamoEntity.toDomain();

        assertEquals("1", pagamento.getId());
        assertEquals("Pedido123", pagamento.getPedidoId());
        assertEquals(new BigDecimal("70.50"), pagamento.getTotal());
        assertEquals(Status.APROVADO, pagamento.getStatus());
        assertEquals("2022-01-28T10:30:00Z", pagamento.getDataDeCriacao());
    }
}
