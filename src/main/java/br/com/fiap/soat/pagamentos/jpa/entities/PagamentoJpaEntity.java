package br.com.fiap.soat.pagamentos.jpa.entities;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
public class PagamentoJpaEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private UUID pedidoId;
    private BigDecimal total;
    private Status status;
    private LocalDateTime dataDeCriacao;

    @OneToMany(mappedBy = "pagamentos", cascade = CascadeType.ALL)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public static PagamentoJpaEntity fromDomain(Pagamento pagamento) {
        PagamentoJpaEntity pagamentoJpaEntity = new PagamentoJpaEntity();

        pagamentoJpaEntity.setId(pagamento.getId());
        pagamentoJpaEntity.setPedidoId(pagamento.getPedidoId());
        pagamentoJpaEntity.setTotal(pagamento.getTotal());
        pagamentoJpaEntity.setStatus(pagamento.getStatus());
        pagamentoJpaEntity.setDataDeCriacao(pagamento.getDataDeCriacao());

        return pagamentoJpaEntity;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
