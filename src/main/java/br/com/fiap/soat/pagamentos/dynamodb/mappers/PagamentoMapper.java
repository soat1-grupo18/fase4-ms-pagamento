package br.com.fiap.soat.pagamentos.dynamodb.mappers;

import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.entities.Pagamento;

public class PagamentoMapper {
    public static Pagamento toDomain(PagamentoDynamoEntity entity) {
        return new Pagamento(entity.getId(), entity.getPedidoId(), entity.getTotal(), entity.getStatus(), entity.getDataDeCriacao());
    }

    public static PagamentoDynamoEntity toEntity(Pagamento pagamento) {
        PagamentoDynamoEntity pagamentoDynamoEntity = new PagamentoDynamoEntity();

        pagamentoDynamoEntity.setId(pagamento.getId());
        pagamentoDynamoEntity.setPedidoId(pagamento.getPedidoId());
        pagamentoDynamoEntity.setTotal(pagamento.getTotal());
        pagamentoDynamoEntity.setStatus(pagamento.getStatus());
        pagamentoDynamoEntity.setDataDeCriacao(pagamento.getDataDeCriacao());

        return pagamentoDynamoEntity;
    }
}
