package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import org.springframework.stereotype.Component;

import java.util.UUID;

public interface CriarPagamentoUseCasePort {
    Pagamento execute(Pagamento pagamento);
}
