package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentoPorPedidoIdUseCasePort;

import java.util.Optional;
import java.util.UUID;

public class ObterPagamentoPorPedidoIdUseCase implements ObterPagamentoPorPedidoIdUseCasePort {

    private final PagamentosGatewayPort pagamentoGateway;

    public ObterPagamentoPorPedidoIdUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Optional<Pagamento> execute(UUID pedidoId) {
        return pagamentoGateway.obterPagamentoPorPedidoId(pedidoId);
    }
}
