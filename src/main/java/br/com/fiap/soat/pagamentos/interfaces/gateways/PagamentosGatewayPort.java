package br.com.fiap.soat.pagamentos.interfaces.gateways;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentosGatewayPort {
    List<Pagamento> obterPagamentosPorStatus(Status status);
    Pagamento criarPagamento(Pagamento pedido);
    Optional<Pagamento> obterPagamentoPorPedidoId(UUID pedidoId);
}
