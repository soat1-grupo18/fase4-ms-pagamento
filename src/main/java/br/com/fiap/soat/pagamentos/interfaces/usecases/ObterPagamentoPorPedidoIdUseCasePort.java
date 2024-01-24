package br.com.fiap.soat.pagamentos.interfaces.usecases;
import br.com.fiap.soat.pagamentos.entities.Pagamento;

import java.util.Optional;
import java.util.UUID;

public interface ObterPagamentoPorPedidoIdUseCasePort {
    Optional<Pagamento> execute(UUID pedidoId);
}
