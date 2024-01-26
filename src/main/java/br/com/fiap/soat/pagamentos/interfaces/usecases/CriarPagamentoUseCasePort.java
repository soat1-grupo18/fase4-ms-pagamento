package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;

public interface CriarPagamentoUseCasePort {
    Pagamento execute(Pagamento pagamento);
}
