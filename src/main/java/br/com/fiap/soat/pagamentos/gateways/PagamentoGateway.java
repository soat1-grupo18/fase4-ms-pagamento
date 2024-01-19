package br.com.fiap.soat.pagamentos.gateways;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;
import br.com.fiap.soat.pagamentos.jpa.repositories.PagamentoRepository;
import br.com.fiap.soat.pagamentos.exceptions.PagamentoNaoEncontradoException;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PagamentoGateway implements PagamentosGatewayPort {
    public PagamentoGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    private final PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public Pagamento inserirPagamento(Pagamento pagamento) {
        PagamentoJpaEntity pagamentoJpaEntity = PagamentoJpaEntity.fromDomain(pagamento);

        pagamentoRepository.save(pagamentoJpaEntity);

        return pagamentoJpaEntity.toDomain();
    }

    @Override
    public Pagamento obterPagamento(UUID pedidoId) {
        Optional<Pagamento> pagamento = Optional.ofNullable(pagamentoRepository.findByPedidoId(pedidoId));

        if (pagamento.isEmpty()) {
            throw PagamentoNaoEncontradoException.aPartirDoId(pedidoId);
        }

        return pagamento.get().toDomain();
    }

    @Override
    public Boolean consultarStatus(UUID pagamentoId) {
        var pagamento = obterPagamento(pagamentoId);
        return pagamento.hasBeenApproved();
    }

    @Override
    public void atualizarPagamento(Pagamento pagamento) {
        PagamentoJpaEntity pagamentoJpaEntity = PagamentoJpaEntity.fromDomain(pagamento);
        pagamentoRepository.save(pagamentoJpaEntity);
    }

    @Override
    public Optional<Pagamento> obterPagamentoComPagamentoId(UUID pagamentoId) {
        var pagamentoO = pagamentoRepository.findById(pagamentoId);
        return Optional.ofNullable(pagamentoO.get().toDomain());
    }

    @Override
    public List<Pagamento> obterPagamentosPorStatus(Status status) {
        return pagamentoRepository.obterPagamentosPorStatus(status).stream()
                .map(PagamentoJpaEntity::toDomain).collect(Collectors.toList());
    }
}
