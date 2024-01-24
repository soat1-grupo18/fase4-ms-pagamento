package br.com.fiap.soat.pagamentos.controllers;

import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentoPorPedidoIdUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentosPorStatusUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.CriarPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PagamentoController {

    private final ObterPagamentoPorPedidoIdUseCasePort obterPagamentoPorPedidoIdUseCase;
    private final ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase;
    private final ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCase;
    private final CriarPagamentoUseCasePort criarPagamentoUseCase;


    public PagamentoController(ObterPagamentoPorPedidoIdUseCasePort obterPagamentoPorPedidoIdUseCase,
                               ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase,
                               ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCase,
                               CriarPagamentoUseCasePort criarPagamentoUseCase) {

        this.obterPagamentoPorPedidoIdUseCase = obterPagamentoPorPedidoIdUseCase;
        this.receberConfirmacaoPagamentoUseCase = receberConfirmacaoPagamentoUseCase;
        this.obterPagamentosPorStatusUseCase = obterPagamentosPorStatusUseCase;
        this.criarPagamentoUseCase = criarPagamentoUseCase;
    }

    public PagamentoPresenter criarPagamento(Pagamento pagamento) {
        return PagamentoPresenter.fromDomain(criarPagamentoUseCase.execute(pagamento));
    }

    public PagamentoPresenter obterPagamentoPorPedidoId(UUID pedidoId) {
        Pagamento pagamento = obterPagamentoPorPedidoIdUseCase.execute(pedidoId)
                .orElseGet(() -> null);

        return PagamentoPresenter.fromDomain(pagamento);
    }

    public String receberConfirmacaoPagamento(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento) {
        return receberConfirmacaoPagamentoUseCase.execute(comandoDeConfirmacaoDePagamento);
    }

    public List<PagamentoPresenter> obterPagamentosPorStatus(Status status) {
        List<Pagamento> pagamentos = obterPagamentosPorStatusUseCase.execute(status);
        return pagamentos.stream().map(PagamentoPresenter::fromDomain).collect(Collectors.toList());
    }
}
