package br.com.fiap.soat.pagamentos.api.requests;

import jakarta.validation.constraints.NotNull;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class PagamentoRequest {
    @NotNull(message = "O campo pedidoId é obrigatório.")
    private String pedidoId;

    @NotNull(message = "O campo total é obrigatório.")
    private BigDecimal total;

    Instant currentInstant = Instant.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String dataDeCriacao = formatter.format(currentInstant.atOffset(ZoneOffset.UTC));

    public Pagamento toDomain() {

        Pagamento pagamento = new Pagamento(pedidoId, total, Status.PENDENTE, dataDeCriacao);
        System.out.print("*************************************");
        System.out.println(pagamento);
        System.out.print("*************************************");

        return pagamento;
    }

    public String getPedidoId() {
        return pedidoId;
    }
    public BigDecimal getTotal() {
        return total;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
