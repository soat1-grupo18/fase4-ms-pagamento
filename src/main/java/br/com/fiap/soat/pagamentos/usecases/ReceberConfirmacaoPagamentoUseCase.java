package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.exceptions.ConfirmacaoDePagamentoInvalidaException;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

import java.util.UUID;

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

        UUID pagamentoId = comandoDeConfirmacaoDePagamento.getPagamentoId();

        Pagamento pagamento = getPagamentoById(pagamentoId);

        if (pagamento == null) {
            throw new RuntimeException("Pagamento não foi encontrado.");
        }

        pagamento.setStatus(Status.APROVADO);

        /* TO-DO:
            request orders microservice to approve order
        * */
       // pedido.setStatusDoPedido("EM_PREPARAÇÃO");
        return String.format("Pagamento %s confirmado", pagamentoId);
    }

    private Pagamento getPagamentoById(UUID pagamentoId) {
        // Implement your logic to retrieve Pagamento by ID from your data source
        // For example, you might use a repository or service to fetch the Pagamento object
        // Return null if the Pagamento is not found
        return null;
    }
}
