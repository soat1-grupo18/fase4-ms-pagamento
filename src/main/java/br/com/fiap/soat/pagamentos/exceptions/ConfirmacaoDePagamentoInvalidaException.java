package br.com.fiap.soat.pagamentos.exceptions;

public class ConfirmacaoDePagamentoInvalidaException extends RuntimeException {
    ConfirmacaoDePagamentoInvalidaException(String message) {
        super(message);
    }

    public static ConfirmacaoDePagamentoInvalidaException aPartirDaAction(String action) {
        return new ConfirmacaoDePagamentoInvalidaException(String.format("Action %s não suportada para confirmação de pagamento.", action));
    }
}
