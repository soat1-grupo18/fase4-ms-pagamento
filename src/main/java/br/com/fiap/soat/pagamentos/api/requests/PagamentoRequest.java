package br.com.fiap.soat.pagamentos.api.requests;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class PagamentoRequest {
    @NotNull(message = "O campo pedidoId é obrigatório.")
    private UUID pedidoId;

    @NotNull(message = "O campo total é obrigatório.")
    private BigDecimal total;

    String dataDeCriacao = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            Locale.ENGLISH).format(System.currentTimeMillis());

    public Pagamento toDomain(UUID id) {
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
