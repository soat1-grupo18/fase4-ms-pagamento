package br.com.fiap.soat.pagamentos.api;

import br.com.fiap.soat.pagamentos.api.requests.ConfirmacaoPagamentoRequest;
import br.com.fiap.soat.pagamentos.controllers.PagamentoController;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "API de Pagamentos")
public class PagamentoApi {

    private final PagamentoController pagamentoController;

    public PagamentoApi(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @Operation(summary = "Obter pagamentos", description = "Retorna uma lista de pagamentos, opcionalmente filtrada por status.")
    @GetMapping("/pagamentos")
    public ResponseEntity<List<PagamentoPresenter>> obterPagamentos(@RequestParam(name = "status", required = false) Status[] statuses) {
        List<PagamentoPresenter> pagamentos;

        if (statuses != null && statuses.length > 0) {
            pagamentos = pagamentoController.obterPagamentosPorStatus(statuses);
        } else {
            pagamentos = pagamentoController.obterTodosPagamentos();
        }

        return ResponseEntity.ok(pagamentos);
    }

    @Operation(summary = "Consultar status de pagamento", description = "Consulta o status de pagamento a partir do id do pedido.")
    @GetMapping("/pagamentos/{pedidoId}")
    public ResponseEntity<PagamentoPresenter> consultarStatus(@PathVariable UUID pedidoId) {
        return ResponseEntity.ok(this.pagamentoController.consultarStatus(pedidoId));
    }

    @Operation(summary = "Receber confirmaçào de pagamento (Webhook)",
            description = "Webhook para receber a confirmação de pagamento enviada pelo MercadoPago.<br>" +
                    "Somente os parâmetros action e data.id serão processados.<br>" +
                    "- action: Ação a ser processada. Somente a confirmação de pagamento é aceita no momento. Exemplo: payment.created<br>" +
                    "- data.id: O id de pagamento registrado no checkout de pedido.")
    @PostMapping("/pagamentos")
    public ResponseEntity<String> receberConfirmacao(@RequestBody ConfirmacaoPagamentoRequest confirmacaoPagamentoRequest) {
        return ResponseEntity.ok(this.pagamentoController.receberConfirmacaoPagamento(confirmacaoPagamentoRequest.toDomain()));
    }
}
