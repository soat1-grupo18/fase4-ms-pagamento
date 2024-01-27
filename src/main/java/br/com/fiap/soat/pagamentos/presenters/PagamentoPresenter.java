package br.com.fiap.soat.pagamentos.presenters;
import br.com.fiap.soat.pagamentos.api.requests.PagamentoRequest;
import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class PagamentoPresenter {
    private static final Logger logger = LoggerFactory.getLogger(PagamentoRequest.class);

    private String id;
    private String pedidoId;
    private Status status;
    private BigDecimal total;
    private String dataDeCriacao;

    public PagamentoPresenter(String id, String pedidoId, Status status, BigDecimal total, String dataDeCriacao) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.status = status;
        this.total = total;
        this.dataDeCriacao = dataDeCriacao;
    }

    public static PagamentoPresenter fromDomain(Pagamento pagamento) {
        logger.info("PagamentoPresenter 2: " + pagamento);

        return new PagamentoPresenter(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getStatus(),
                pagamento.getTotal(),
                pagamento.getDataDeCriacao()
        );
    }

    public String getId() {
        return id;
    }
    public String getPedidoId() {
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
