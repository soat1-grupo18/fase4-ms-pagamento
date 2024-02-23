package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.exceptions.ConfirmacaoDePagamentoInvalidaException;
import br.com.fiap.soat.pagamentos.interfaces.gateways.ProducaoGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

import java.util.Optional;
import java.util.UUID;

public class ReceberConfirmacaoPagamentoUseCase implements ReceberConfirmacaoPagamentoUseCasePort {
    private final PagamentosGatewayPort pagamentoGateway;
    private final ProducaoGatewayPort producaoGateway;

    public ReceberConfirmacaoPagamentoUseCase(PagamentosGatewayPort pagamentoGateway,
                                              ProducaoGatewayPort producaoGateway) {
        this.pagamentoGateway = pagamentoGateway;
        this.producaoGateway = producaoGateway;
    }

    public String execute(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento) {
        String action = comandoDeConfirmacaoDePagamento.getAction();
        if (!action.equals("payment.created")) {
            throw ConfirmacaoDePagamentoInvalidaException.aPartirDaAction(action);
        }

        String id = comandoDeConfirmacaoDePagamento.getPagamentoId();

        Optional<Pagamento> optPagamento = pagamentoGateway.obterPagamentoPorId(id);

        if (optPagamento.isPresent()) {
            PagamentoDynamoEntity pagamento = PagamentoDynamoEntity.fromDomain(optPagamento.get());
            pagamento.setStatus(Status.APROVADO);

            producaoGateway.atualizarStatus(UUID.fromString(pagamento.getPedidoId()));

            return String.format("Pagamento %s confirmado", id);
        } else {
            throw new RuntimeException("Pagamento não foi encontrado.");
        }
    }
}
