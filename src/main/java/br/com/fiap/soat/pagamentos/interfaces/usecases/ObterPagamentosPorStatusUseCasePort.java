package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import java.util.List;

public interface ObterPagamentosPorStatusUseCasePort {
     List<Pagamento> execute(Status status);
}