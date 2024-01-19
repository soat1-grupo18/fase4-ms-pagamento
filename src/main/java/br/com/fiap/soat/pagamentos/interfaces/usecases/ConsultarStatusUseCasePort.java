package br.com.fiap.soat.pagamentos.interfaces.usecases;


import java.util.UUID;

public interface ConsultarStatusUseCasePort {
    Object execute(UUID pedidoId);
}
