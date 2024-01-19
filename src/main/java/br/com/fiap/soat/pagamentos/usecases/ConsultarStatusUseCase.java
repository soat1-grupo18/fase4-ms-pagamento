package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ConsultarStatusUseCasePort;

import java.util.UUID;

public class ConsultarStatusUseCase implements ConsultarStatusUseCasePort {

    private final PagamentosGatewayPort pagamentoGateway;

    public ConsultarStatusUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Boolean execute(UUID pedidoId) {
        return pagamentoGateway.consultarStatus(pedidoId);
    }
}
