package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentoPorIdUseCasePort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Component
public class ObterPagamentoPorIdUseCase implements ObterPagamentoPorIdUseCasePort {

    private final PagamentosGatewayPort pagamentoGateway;

    public ObterPagamentoPorIdUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Optional<Pagamento> execute(UUID id) {
        return pagamentoGateway.obterPagamentoPorId(id);
    }
}
