package br.com.fiap.soat.pagamentos.interfaces.gateways;

import java.util.UUID;

public interface ProducaoGatewayPort {
    void atualizarStatus(UUID pedidoOriginalId);
}
