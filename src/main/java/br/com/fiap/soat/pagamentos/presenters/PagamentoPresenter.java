package br.com.fiap.soat.pagamentos.presenters;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import java.math.BigDecimal;
import java.util.UUID;

public class PagamentoPresenter {
    private UUID id;
    private BigDecimal total;
    private Status status;
    private UUID pedidoId;

    public PagamentoPresenter(Status status) {
        this.status = status;
    }

    public static PagamentoPresenter fromDomain(Pagamento pagamento) {
        PagamentoPresenter pagamentoPresenter = new PagamentoPresenter(pagamento.getStatus());
        pagamentoPresenter.id = pagamento.getId();
        pagamentoPresenter.total = pagamento.getTotal();
        pagamentoPresenter.status = pagamento.getStatus();
        pagamentoPresenter.pedidoId = pagamento.getPedidoId();

        return pagamentoPresenter;
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
}
