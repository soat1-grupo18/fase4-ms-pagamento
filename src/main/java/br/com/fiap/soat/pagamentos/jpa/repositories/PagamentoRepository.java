package br.com.fiap.soat.pagamentos.jpa.repositories;

import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PagamentoRepository extends CrudRepository<PagamentoJpaEntity, UUID> {
    List<PagamentoJpaEntity> findByPedidoId(UUID pedidoId);
    List<PagamentoJpaEntity> obterPagamentosPorStatus(Status[] status);


//    @Query("select p from PedidoJpaEntity p " +
//            "where p.statusDoPedido IN :statuses " +
//            "order by p.dataDeCriacao asc, p.statusDoPedido desc")
//    List<PedidoJpaEntity> obterPedidosPorStatus(StatusDoPedido... statuses);
}
