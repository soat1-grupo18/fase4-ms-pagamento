package br.com.fiap.soat.pagamentos.acceptance.steps;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.jpa.entities.PagamentoJpaEntity;
import br.com.fiap.soat.pagamentos.jpa.repositories.PagamentoRepository;
import br.com.fiap.soat.pagamentos.presenters.PagamentoPresenter;
import br.com.fiap.soat.pagamentos.usecases.CriarPagamentoUseCase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Collections;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class getPaymentByStatusSteps {
    private CriarPagamentoUseCase criarPagamentoUseCase;
    private PagamentoRepository pagamentoRepository;
    private PagamentoPresenter pagamentoPresenter;

    private List<Pagamento> foundPagamentos;

    @Given("there are pagamentos with status PENDENTE")
    public void thereArePagamentosWithStatusPENDENTE() {
        Pagamento pagamento = criarPagamento();
        criarPagamentoUseCase.execute(pagamento);
    }

    @When("the client requests the endpoint \\\\/pagamentos\\\\?status=PENDENTE")
    public void clientRequestsEndpointWithStatusPENDENTE() {
        List<PagamentoJpaEntity> pagamentos = pagamentoRepository.obterPagamentosPorStatus(Status.PENDENTE);
        ArrayList foundPagamentos = new ArrayList<>(pagamentos);
        System.out.println(foundPagamentos);
    }

    @Then("a list of PENDENTE pagamentos is returned to the client")
    public void listOfPENDENTEPagamentosIsReturned() {
        List<Pagamento> resultList = foundPagamentos
                .stream()
                .toList();

        Assert.assertEquals(
                resultList.stream().map(Pagamento::getStatus).collect(Collectors.toList()),
                Collections.singletonList(Status.PENDENTE)
        );
    }

    private Pagamento criarPagamento( ) {
        var pagamento = new Pagamento();
        pagamento.setId(UUID.randomUUID());
        pagamento.setStatus(Status.PENDENTE);
        pagamento.setPedidoId(UUID.randomUUID());
        pagamento.setDataDeCriacao(String.valueOf(LocalDateTime.now()));

        return pagamento;
    }
}
