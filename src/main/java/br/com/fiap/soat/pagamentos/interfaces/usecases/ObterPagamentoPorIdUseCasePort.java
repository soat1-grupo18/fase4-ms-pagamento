package br.com.fiap.soat.pagamentos.interfaces.usecases;
import br.com.fiap.soat.pagamentos.entities.Pagamento;

import java.util.Optional;

public interface ObterPagamentoPorIdUseCasePort {
    Optional<Pagamento> execute(String id);
}
