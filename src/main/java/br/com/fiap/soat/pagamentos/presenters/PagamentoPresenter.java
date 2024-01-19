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

    private final Boolean pagamentoAprovado;

    public PagamentoPresenter(Boolean pagamentoAprovado) {

        this.pagamentoAprovado = pagamentoAprovado;
    }

    public boolean isPagamentoAprovado() {
        return pagamentoAprovado;
    }

    public static PagamentoPresenter fromDomain(Pagamento pagamento) {
        PagamentoPresenter pagamentoPresenter = new PagamentoPresenter(pagamento.getStatus().equals(Status.APROVADO));

        pagamentoPresenter.id = pagamento.getId();
        pagamentoPresenter.total = pagamento.getTotal();
        pagamentoPresenter.status = pagamento.getStatus();
        pagamentoPresenter.pedidoId = pagamento.getPedidoId();

        return pagamentoPresenter;
    }
    public UUID getId() {
        return id;
    }

    public UUID getPedidoId() { return pedidoId; }
    public BigDecimal getTotal() {
        return total;
    }
    public Status getStatus() {
        return status;
    }



}
