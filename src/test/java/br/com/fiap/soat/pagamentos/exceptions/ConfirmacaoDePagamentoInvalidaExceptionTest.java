package br.com.fiap.soat.pagamentos.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConfirmacaoDePagamentoInvalidaExceptionTest {
    @Test
    void criaExceptionComMensagem() {
        String errorMessage = "Test error message";
        ConfirmacaoDePagamentoInvalidaException exception = new ConfirmacaoDePagamentoInvalidaException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void criaExceptionAPartirDaAction() {
        String action = "TestAction";
        ConfirmacaoDePagamentoInvalidaException exception = ConfirmacaoDePagamentoInvalidaException.aPartirDaAction(action);

        String expectedMessage = String.format("Action %s não suportada para confirmação de pagamento.", action);
        assertEquals(expectedMessage, exception.getMessage());
    }

}
