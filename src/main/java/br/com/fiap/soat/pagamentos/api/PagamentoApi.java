package br.com.fiap.soat.pagamentos.api;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.api.requests.PagamentoRequest;
import br.com.fiap.soat.pagamentos.api.requests.ConfirmacaoPagamentoRequest;
import br.com.fiap.soat.pagamentos.controllers.PagamentoController;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.UUID;
import java.util.List;

@RestController
@Tag(name = "API de Pagamentos")
public class PagamentoApi {
    private final PagamentoController pagamentoController;

    public PagamentoApi(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @Operation(summary = "Criar um pagamento",
            description = "Cria um pagamento a partir de um id do pedido e seu total.")
    @PostMapping("/pagamentos")
    public ResponseEntity<PagamentoPresenter> criarPagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest) {

        return ResponseEntity.ok(pagamentoController.criarPagamento(pagamentoRequest.toDomain(null)));
    }

    @Operation(summary = "Obter pagamentos", description = "Retorna uma lista de pagamentos filtrada por status.")
    @GetMapping("/pagamentos")
    public ResponseEntity<List<PagamentoPresenter>> obterPagamentos(@RequestParam(name = "status", required = true) Status status) {
        List<PagamentoPresenter> pagamentos;

        pagamentos = pagamentoController.obterPagamentosPorStatus(status);

        return ResponseEntity.ok(pagamentos);
    }

    @Operation(summary = "Retorna o pagamento", description = "Retorna o pagamento a partir do id.")
    @GetMapping("/pagamentos")
    public ResponseEntity<PagamentoPresenter> obterPagamentoPorId(@RequestParam(name = "id", required = true) UUID id) {
        return ResponseEntity.ok(pagamentoController.obterPagamentoPorId(id));
    }

    @Operation(summary = "Receber confirmaçào de pagamento (Webhook)",
            description = "Webhook para receber a confirmação de pagamento enviada pelo MercadoPago.<br>" +
                    "Somente os parâmetros action e data.id serão processados.<br>" +
                    "- action: Ação a ser processada. Somente a confirmação de pagamento é aceita no momento. Exemplo: payment.created<br>" +
                    "- data.id: O id de pagamento registrado no checkout de pedido.")
    @PostMapping("/pagamentos/confirmar")
    public ResponseEntity<String> receberConfirmacao(@RequestBody ConfirmacaoPagamentoRequest confirmacaoPagamentoRequest) {
        return ResponseEntity.ok(pagamentoController.receberConfirmacaoPagamento(confirmacaoPagamentoRequest.toDomain()));
    }
}
