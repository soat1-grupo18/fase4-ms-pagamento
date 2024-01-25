package br.com.fiap.soat.pagamentos.acceptance.steps;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.entities.Status;
import br.com.fiap.soat.pagamentos.interfaces.usecases.CriarPagamentoUseCasePort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.ObterPagamentosPorStatusUseCasePort;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetPagamentosByStatusSteps {
    @Autowired
    private CriarPagamentoUseCasePort criarPagamentoUseCase;
    @Autowired
    private ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCase;
    private List<Pagamento> foundPagamentos;

    @Given("there are pagamentos with status PENDENTE")
    public void thereArePagamentosWithStatusPENDENTE() {
        Pagamento pagamento = criarPagamento();
        criarPagamentoUseCase.execute(pagamento);
    }

    @When("the client requests the endpoint with status PENDENTE")
    public void clientRequestsEndpointWithStatusPENDENTE() {
        List<Pagamento> pagamentos = obterPagamentosPorStatusUseCase.execute(Status.PENDENTE);
        foundPagamentos = new ArrayList<>(pagamentos);
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
