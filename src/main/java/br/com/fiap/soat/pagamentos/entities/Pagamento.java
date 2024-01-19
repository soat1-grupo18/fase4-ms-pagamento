package br.com.fiap.soat.pagamentos.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pagamento {
    private UUID id;
    private UUID pedidoId;
    private BigDecimal total;
    private Status status;
    private LocalDateTime dataDeCriacao;


    public Status buscarStatus() {
        return status;
    }

    public UUID buscarPedidoId() {
        return pedidoId;
    }

    public Pagamento(
            UUID id,
            UUID pedidoId,
            BigDecimal total,
            Status status,
            LocalDateTime dataDeCriacao
    ) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.total = total;
        this.status = status;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Pagamento() {
        this.total = BigDecimal.ZERO;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public boolean hasBeenApproved() {
        return this.status == Status.APROVADO;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Pagamento toDomain() {
        return new Pagamento(
                id,
                pedidoId,
                total,
                status,
                dataDeCriacao
        );
    }
}
