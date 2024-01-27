package br.com.fiap.soat.pagamentos.dynamodb.repositories;

import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface PagamentoRepository extends DynamoDBCrudRepository<PagamentoDynamoEntity, String> {
    Optional<PagamentoDynamoEntity> findById(String id);
    List<PagamentoDynamoEntity> findByStatus(Status status);
}
