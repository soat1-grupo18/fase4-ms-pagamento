package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentosPorStatusUseCasePort;

import java.util.List;

public class ObterPagamentosPorStatusUseCase implements ObterPagamentosPorStatusUseCasePort {

    private final PagamentoGatewayPort pagamentoGateway;

    public ObterPedidosPorStatusUseCase(PagamentoGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public List<Pedido> execute(Status... statuses) {
        return pagamentoGateway.obterPagamentosPorStatus(statuses);
    }
}
