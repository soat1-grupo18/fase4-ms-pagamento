package br.com.fiap.soat.pagamentos.controllers;

import br.com.fiap.soat.pagamentos.interfaces.usecases.ConsultarStatusUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentosPorStatusUseCasePort;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

public class PagamentoController {

    private final ConsultarStatusUseCasePort consultarStatusUseCase;
    private final ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase;
    private final ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCase;

    public PagamentoController(ConsultarStatusUseCasePort consultarStatusUseCase,
                               ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase,
                               ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCase) {

        this.consultarStatusUseCase = consultarStatusUseCase;
        this.receberConfirmacaoPagamentoUseCase = receberConfirmacaoPagamentoUseCase;
        this.obterPagamentosPorStatusUseCase = obterPagamentosPorStatusUseCase;
    }

    public PagamentoPresenter consultarStatus(UUID pedidoId) {
        Status status = consultarStatusUseCase.execute(pedidoId);
        return new PagamentoPresenter(status);
    }

    public String receberConfirmacaoPagamento(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento) {
        return receberConfirmacaoPagamentoUseCase.execute(comandoDeConfirmacaoDePagamento);
    }

    public List<PagamentoPresenter> obterPagamentosPorStatus(Status status) {
        List<Pagamento> pagamentos = obterPagamentosPorStatusUseCase.execute(status);
        return pagamentos.stream().map(PagamentoPresenter::fromDomain).collect(Collectors.toList());
    }
}
