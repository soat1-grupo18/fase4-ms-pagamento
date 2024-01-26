package br.com.fiap.soat.pagamentos.gateways;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.dynamodb.repositories.PagamentoRepository;
import br.com.fiap.soat.pagamentos.exceptions.PagamentoNaoEncontradoException;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;

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
    public Pagamento criarPagamento(Pagamento pagamento) {
        PagamentoDynamoEntity pagamentoDynamoEntity = PagamentoDynamoEntity.fromDomain(pagamento);

        pagamentoRepository.save(pagamentoDynamoEntity);

        return pagamentoDynamoEntity.toDomain();
    }

    @Override
    public Optional<Pagamento> obterPagamentoPorId(UUID id) {
        Optional<PagamentoDynamoEntity> pagamentoEntity = pagamentoRepository.findById(id);

        if (pagamentoEntity.isEmpty()) {
            throw PagamentoNaoEncontradoException.aPartirDoId(id);
        }
    
        return Optional.ofNullable(pagamentoEntity.get().toDomain());
    }

    @Override
    public List<Pagamento> obterPagamentosPorStatus(Status status) {
        return pagamentoRepository.findByStatus(status).stream()
                .map(PagamentoDynamoEntity::toDomain).collect(Collectors.toList());
    }
}
