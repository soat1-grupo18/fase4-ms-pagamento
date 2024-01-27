package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;

public interface ReceberConfirmacaoPagamentoUseCasePort {
    String execute(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento);
}
