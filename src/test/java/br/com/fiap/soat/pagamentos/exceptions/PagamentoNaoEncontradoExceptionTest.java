package br.com.fiap.soat.pagamentos.exceptions;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoNaoEncontradoExceptionTest {
    @Test
    void criaExceptionComMensagem() {
        String errorMessage = "Test error message";
        PagamentoNaoEncontradoException exception = new PagamentoNaoEncontradoException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void criaExceptionAPartirDoId() {
        String id = "TestOrderId";
        PagamentoNaoEncontradoException exception = PagamentoNaoEncontradoException.aPartirDoId(id);

        String expectedMessage = String.format("Pagamento não encontrado pelo pedido id: %s", id);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
