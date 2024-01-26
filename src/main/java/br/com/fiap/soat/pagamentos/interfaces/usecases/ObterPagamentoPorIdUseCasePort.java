package br.com.fiap.soat.pagamentos.interfaces.usecases;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

public interface ObterPagamentoPorIdUseCasePort {
    Optional<Pagamento> execute(String id);
}
