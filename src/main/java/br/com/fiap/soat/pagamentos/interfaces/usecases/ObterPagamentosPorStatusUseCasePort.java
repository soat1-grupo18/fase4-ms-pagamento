package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ObterPagamentosPorStatusUseCasePort {
     List<Pagamento> execute(Status status);
}