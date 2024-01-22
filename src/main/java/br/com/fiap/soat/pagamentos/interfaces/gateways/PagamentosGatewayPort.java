package br.com.fiap.soat.pagamentos.interfaces.gateways;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentosGatewayPort {
    Pagamento inserirPagamento(Pagamento pedido);
    Pagamento obterPagamentoComPedidoId(UUID pedidoId);
    Boolean consultarStatus(UUID pagamentoId);
    Optional<Pagamento> obterPagamentoComPagamentoId(UUID pagamentoId);
    List<Pagamento> obterPagamentosPorStatus(Status status);
}
