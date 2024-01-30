package br.com.fiap.soat.pagamentos.gateways;

import br.com.fiap.soat.pagamentos.interfaces.gateways.ProducaoGatewayPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProducaoGateway implements ProducaoGatewayPort {

    private final WebClient producaoWebClient;

    public ProducaoGateway(WebClient producaoWebClient) {
        this.producaoWebClient = producaoWebClient;
    }

    @Override
    public void atualizarStatus(UUID pedidoOriginalId) {
        producaoWebClient.put()
                .uri("/pedidos/{pedidoId}/EM_PREPARACAO", pedidoOriginalId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new WebClientResponseException
                        (response.statusCode().value(), "Bad Request", null, null, null)))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new WebClientResponseException
                        (response.statusCode().value(), "Server Error", null, null, null)))
                .toEntity(Void.class)
                .block();
    }

}
