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
    public Optional<Pagamento> obterPagamentoComPedidoId(UUID pedidoId) {
        Optional<PagamentoJpaEntity> pagamentoEntity = pagamentoRepository.findByPedidoId(pedidoId);
    
        if (pagamentoEntity.isEmpty()) {
            throw PagamentoNaoEncontradoException.aPartirDoId(pedidoId);
        }
    
        return Optional.ofNullable(pagamentoEntity.get().toDomain());
    }
    
    @Override
    public Status consultarStatus(UUID pedidoId) {
        var pagamentoOpt = obterPagamentoComPedidoId(pedidoId);

        if (pagamentoOpt.isPresent()) {
            return pagamentoOpt.get().getStatus();
        } else {
            throw PagamentoNaoEncontradoException.aPartirDoId(pedidoId);
        }
    }

    @Override
    public Optional<PagamentoJpaEntity> obterPagamentoPorId(UUID id) {
        return pagamentoRepository.findById(id);
    }

    @Override
    public List<Pagamento> obterPagamentosPorStatus(Status status) {
        return pagamentoRepository.obterPagamentosPorStatus(status).stream()
                .map(PagamentoJpaEntity::toDomain).collect(Collectors.toList());
    }
}
