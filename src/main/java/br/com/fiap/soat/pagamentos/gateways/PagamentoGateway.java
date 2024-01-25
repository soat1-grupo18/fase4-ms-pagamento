package br.com.fiap.soat.pagamentos.gateways;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;
import br.com.fiap.soat.pagamentos.jpa.repositories.PagamentoRepository;
import br.com.fiap.soat.pagamentos.exceptions.PagamentoNaoEncontradoException;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PagamentoGateway implements PagamentosGatewayPort {
    @Autowired
    public PagamentoGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Autowired
    private final PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public Pagamento criarPagamento(Pagamento pagamento) {
        PagamentoJpaEntity pagamentoJpaEntity = PagamentoJpaEntity.fromDomain(pagamento);

        pagamentoRepository.save(pagamentoJpaEntity);

        return pagamentoJpaEntity.toDomain();
    }

    @Override
    public Optional<Pagamento> obterPagamentoPorId(UUID id) {
        Optional<PagamentoJpaEntity> pagamentoEntity = pagamentoRepository.findById(id);

        if (pagamentoEntity.isEmpty()) {
            throw PagamentoNaoEncontradoException.aPartirDoId(id);
        }
    
        return Optional.ofNullable(pagamentoEntity.get().toDomain());
    }

    @Override
    public List<Pagamento> obterPagamentosPorStatus(Status status) {
        return pagamentoRepository.obterPagamentosPorStatus(status).stream()
                .map(PagamentoJpaEntity::toDomain).collect(Collectors.toList());
    }
}
