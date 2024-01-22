package br.com.fiap.soat.pagamentos.jpa.repositories;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoJpaEntity, UUID> {
    List<PagamentoJpaEntity> consultarStatus(UUID pedidoId);

    List<PagamentoJpaEntity> obterPagamentosPorStatus(Status status);

    Pagamento obterPagamentoComPagamentoId(UUID pagamentoId);

    Pagamento obterPagamentoComPedidoId(UUID pedidoId);
}
