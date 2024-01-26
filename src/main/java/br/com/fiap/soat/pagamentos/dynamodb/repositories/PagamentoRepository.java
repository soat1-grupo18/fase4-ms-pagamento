package br.com.fiap.soat.pagamentos.dynamodb.repositories;

import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.dynamodb.entities.PagamentoDynamoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends CrudRepository<PagamentoDynamoEntity, UUID> {
    Optional<PagamentoDynamoEntity> findById(UUID id);
    List<PagamentoDynamoEntity> findByStatus(Status status);
}
