package br.com.fiap.soat.pagamentos.exceptions;

public class PagamentoNaoEncontradoException extends RuntimeException {
    PagamentoNaoEncontradoException(String message) {
        super(message);
    }

    public static PagamentoNaoEncontradoException aPartirDoId(String id) {
        return new PagamentoNaoEncontradoException(String.format("Pagamento não encontrado pelo pedido id: %s", id));
    }
}
