package br.com.fiap.soat.pagamentos.presenters;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import java.math.BigDecimal;
import java.util.UUID;

public class PagamentoPresenter {
    private UUID id;
    private UUID pedidoId;
    private Status status;
    private BigDecimal total;
    private String dataDeCriacao;


    public PagamentoPresenter(UUID id, UUID pedidoId, Status status, BigDecimal total, String dataDeCriacao) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.status = status;
        this.total = total;
        this.dataDeCriacao = dataDeCriacao;
    }

    public static PagamentoPresenter fromDomain(Pagamento pagamento) {
        return new PagamentoPresenter(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getStatus(),
                pagamento.getTotal(),
                pagamento.getDataDeCriacao()
        );
    }

    public UUID getId() {
        return id;
    }
    public UUID getPedidoId() { 
        return pedidoId; 
    }
    public BigDecimal getTotal() {
        return total;
    }
    public Status getStatus() {
        return status;
    }
    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

}
