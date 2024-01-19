package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentosPorStatusUseCasePort;

import java.util.List;

public class ObterPagamentosPorStatusUseCase implements ObterPagamentosPorStatusUseCasePort {
    private final PagamentosGatewayPort pagamentoGateway;

    public ObterPagamentosPorStatusUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    public List<Pagamento> execute(Status status) {
        return pagamentoGateway.obterPagamentosPorStatus(status);
    }
}




