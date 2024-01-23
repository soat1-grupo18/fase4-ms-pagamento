package br.com.fiap.soat.pagamentos.exceptions;
import java.util.UUID;

public class PagamentoNaoEncontradoException extends RuntimeException {
    PagamentoNaoEncontradoException(String message) {
        super(message);
    }

    public static PagamentoNaoEncontradoException aPartirDoId(UUID id) {
        return new PagamentoNaoEncontradoException(String.format("Pagamento não encontrado pelo pedido id: ", id));
    }
}
