package br.com.fiap.soat.pagamentos.controllers;

import br.com.fiap.soat.pagamentos.interfaces.usecases.ConsultarStatusUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.obterPagamentosPorStatusUseCase;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

import java.util.UUID;

public class PagamentoController {

    private final ConsultarStatusUseCasePort consultarStatusUseCase;
    private final ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase;

    public PagamentoController(ConsultarStatusUseCasePort consultarStatusUseCase,
                               ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase) {

        this.consultarStatusUseCase = consultarStatusUseCase;
        this.receberConfirmacaoPagamentoUseCase = receberConfirmacaoPagamentoUseCase;
    }

    public PagamentoPresenter consultarStatus(UUID pedidoId) {
        Boolean pagamentoAprovado = Status.APROVADO.equals(consultarStatusUseCase.execute(pedidoId));
        return new PagamentoPresenter(pagamentoAprovado);
    }

    public String receberConfirmacaoPagamento(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento) {
        return receberConfirmacaoPagamentoUseCase.execute(comandoDeConfirmacaoDePagamento);
    }

    public List<PagamentoPresenter> obterPagamentosPorStatus(Status... statuses) {
        List<Pagamentos> pagamentos = obterPagamentosPorStatusUseCase.execute(statuses);
        return pagamentos.stream().map(PagamentoPresenter::fromDomain).collect(Collectors.toList());
    }
}
