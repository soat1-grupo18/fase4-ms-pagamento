package br.com.fiap.soat.pagamentos.api;

import br.com.fiap.soat.pagamentos.api.requests.ConfirmacaoPagamentoRequest;
import br.com.fiap.soat.pagamentos.api.requests.PagamentoRequest;
import br.com.fiap.soat.pagamentos.config.CoreExceptionsAdvicer;
import br.com.fiap.soat.pagamentos.controllers.PagamentoController;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PagamentoApiTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        PagamentoApi pagamentoApi = new PagamentoApi(pagamentoController);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoApi)
                .setControllerAdvice(new CoreExceptionsAdvicer())
                .build();
    }

    @Test
    void criarPagamento() throws Exception {
        String pedidoId = UUID.randomUUID().toString();
        BigDecimal total = BigDecimal.valueOf(15);

        var request = new PagamentoRequest();
        request.setPedidoId(pedidoId);
        request.setTotal(total);

        var pagamento = new Pagamento(pedidoId, total, Status.PENDENTE, LocalDateTime.now().toString());

        when(pagamentoController.criarPagamento(any()))
                .thenAnswer(i -> PagamentoPresenter.fromDomain(pagamento));

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());

        verify(pagamentoController, times(1)).criarPagamento(any());
    }

    @Test
    void obterPagamentos() throws Exception {

        var pagamentoId = UUID.randomUUID().toString();
        Pagamento pagamento = new Pagamento(pagamentoId, UUID.randomUUID().toString(), BigDecimal.valueOf(15), Status.PENDENTE, LocalDateTime.now().toString());
        List<PagamentoPresenter> pagamentos = new ArrayList<>();
        pagamentos.add(PagamentoPresenter.fromDomain(pagamento));

        when(pagamentoController.obterPagamentosPorStatus(Status.PENDENTE))
                .thenReturn(pagamentos);

        mockMvc.perform(get("/pagamentos/status/{status}", Status.PENDENTE))
                .andExpect(status().isOk());

        verify(pagamentoController, times(1)).obterPagamentosPorStatus(Status.PENDENTE);
    }

    @Test
    void obterPagamentoPorId() throws Exception {
        var pagamentoId = UUID.randomUUID().toString();
        Pagamento pagamento = new Pagamento(pagamentoId, UUID.randomUUID().toString(), BigDecimal.valueOf(15), Status.PENDENTE, LocalDateTime.now().toString());

        when(pagamentoController.obterPagamentoPorId(pagamentoId))
                .thenReturn(PagamentoPresenter.fromDomain(pagamento));

        mockMvc.perform(get("/pagamentos/{pagamentoId}", pagamentoId))
                .andExpect(status().isOk());

        verify(pagamentoController, times(1)).obterPagamentoPorId(pagamentoId);
    }

    @Test
    void receberConfirmacao() throws Exception {
        var request = new ConfirmacaoPagamentoRequest();

        when(pagamentoController.receberConfirmacaoPagamento(any()))
                .thenReturn("OK");

        mockMvc.perform(post("/pagamentos/confirmar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());

        verify(pagamentoController, times(1)).receberConfirmacaoPagamento(any());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}