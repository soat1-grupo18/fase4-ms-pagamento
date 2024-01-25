package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;
import org.springframework.stereotype.Component;

@Component
public interface ReceberConfirmacaoPagamentoUseCasePort {
    String execute(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento);
}
