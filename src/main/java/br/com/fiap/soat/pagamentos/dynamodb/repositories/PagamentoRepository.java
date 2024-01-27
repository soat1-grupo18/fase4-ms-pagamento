package br.com.fiap.soat.pagamentos.dynamodb.repositories;

import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import org.springframework.data.repository.CrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository extends CrudRepository<PagamentoDynamoEntity, String> {
    Optional<PagamentoDynamoEntity> findById(String id);

    @EnableScan
    List<PagamentoDynamoEntity> findByStatus(Status status);
}
