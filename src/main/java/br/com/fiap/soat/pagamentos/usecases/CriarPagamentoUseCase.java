package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.api.requests.PagamentoRequest;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.CriarPagamentoUseCasePort;


public class CriarPagamentoUseCase implements CriarPagamentoUseCasePort {

    private final PagamentosGatewayPort pagamentoGateway;


    public CriarPagamentoUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pagamento execute(Pagamento pagamento) {
        return pagamentoGateway.criarPagamento(pagamento);
    }
}

