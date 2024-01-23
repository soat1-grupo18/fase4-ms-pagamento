package br.com.fiap.soat.pagamentos.interfaces.usecases;

import br.com.fiap.soat.pagamentos.entities.Status;
import java.util.UUID;

public interface ConsultarStatusUseCasePort {
    Status execute(UUID pedidoId);
}
