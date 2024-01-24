package br.com.fiap.soat.pagamentos.jpa.mappers;

import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;
import br.com.fiap.soat.pagamentos.entities.Pagamento;

public class PagamentoMapper {
    public static Pagamento toDomain(PagamentoJpaEntity entity) {
        return new Pagamento(entity.getId(), entity.getPedidoId(), entity.getTotal(), entity.getStatus(), entity.getDataDeCriacao());
    }

    public static PagamentoJpaEntity toEntity(Pagamento pagamento) {
        PagamentoJpaEntity pagamentoJpaEntity = new PagamentoJpaEntity();

        pagamentoJpaEntity.setId(pagamento.getId());
        pagamentoJpaEntity.setPedidoId(pagamento.getPedidoId());
        pagamentoJpaEntity.setTotal(pagamento.getTotal());
        pagamentoJpaEntity.setStatus(pagamento.getStatus());
        pagamentoJpaEntity.setDataDeCriacao(pagamento.getDataDeCriacao());

        return pagamentoJpaEntity;
    }
}
