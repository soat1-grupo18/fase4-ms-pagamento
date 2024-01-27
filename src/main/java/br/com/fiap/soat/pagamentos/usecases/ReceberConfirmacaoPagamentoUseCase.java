package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.exceptions.ConfirmacaoDePagamentoInvalidaException;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

import java.util.Optional;

public class ReceberConfirmacaoPagamentoUseCase implements ReceberConfirmacaoPagamentoUseCasePort {
    private final PagamentosGatewayPort pagamentoGateway;

    public ReceberConfirmacaoPagamentoUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
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

            /* TO-DO:
                request orders microservice to approve order
            * */
            return String.format("Pagamento %s confirmado", id);
        } else {
            throw new RuntimeException("Pagamento não foi encontrado.");
        }
    }
}
