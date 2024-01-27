package br.com.fiap.soat.pagamentos.gateways;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import br.com.fiap.soat.pagamentos.dynamodb.repositories.PagamentoRepository;
import br.com.fiap.soat.pagamentos.exceptions.PagamentoNaoEncontradoException;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
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
    public Pagamento criarPagamento(Pagamento pagamento) {
        PagamentoDynamoEntity pagamentoDynamoEntity = PagamentoDynamoEntity.fromDomain(pagamento);

        try {
            pagamentoRepository.save(pagamentoDynamoEntity);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return pagamentoDynamoEntity.toDomain();
    }

    @Override
    public Optional<Pagamento> obterPagamentoPorId(UUID id) {
        Optional<PagamentoDynamoEntity> pagamentoEntity = pagamentoRepository.findById(String.valueOf(id));

        if (pagamentoEntity.isEmpty()) {
            throw PagamentoNaoEncontradoException.aPartirDoId(id);
        }
    
        return Optional.ofNullable(pagamentoEntity.get().toDomain());
    }

    @Override
    public List<Pagamento> obterPagamentosPorStatus(Status status) {
        List<PagamentoDynamoEntity> entities = pagamentoRepository.findByStatus(status);

        List<Pagamento> pagamentos = entities.stream()
                .filter(Objects::nonNull)
                .map(PagamentoDynamoEntity::toDomain)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        return pagamentos;
    }

}
