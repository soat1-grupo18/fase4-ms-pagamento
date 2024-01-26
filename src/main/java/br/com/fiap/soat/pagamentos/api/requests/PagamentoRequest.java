package br.com.fiap.soat.pagamentos.api.requests;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class PagamentoRequest {
    @NotNull(message = "O campo pedidoId é obrigatório.")
    private UUID pedidoId;

    @NotNull(message = "O campo total é obrigatório.")
    private BigDecimal total;

    Instant currentInstant = Instant.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String dataDeCriacao = formatter.format(currentInstant.atOffset(ZoneOffset.UTC));

    public Pagamento toDomain(String id) {
        return new Pagamento(id, pedidoId, total, Status.PENDENTE, dataDeCriacao);
    }

    public UUID getPedidoId() {
        return pedidoId;
    }
    public BigDecimal getTotal() {
        return total;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
