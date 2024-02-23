package br.com.fiap.soat.pagamentos.config;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.gateways.ProducaoGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.*;
import br.com.fiap.soat.pagamentos.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {
    @Bean("criarPagamentoUseCasePort")
    public CriarPagamentoUseCasePort criarPagamentoUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new CriarPagamentoUseCase(pagamentoGatewayPort);
    }

    @Bean("obterPagamentoPorIdUseCasePort")
    public ObterPagamentoPorIdUseCasePort obterPagamentoPorIdUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new ObterPagamentoPorIdUseCase(pagamentoGatewayPort);
    }

    @Bean("receberConfirmacaoPagamentoUseCasePort")
    public ReceberConfirmacaoPagamentoUseCasePort ReceberConfirmacaoPagamentoUseCasePort(PagamentosGatewayPort pagamentoGatewayPort,
                                                                                            ProducaoGatewayPort producaoGatewayPort) {
        return new ReceberConfirmacaoPagamentoUseCase(pagamentoGatewayPort, producaoGatewayPort);
    }

    @Bean("obterPagamentosPorStatusUseCasePort")

    public ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new ObterPagamentosPorStatusUseCase(pagamentoGatewayPort);
    }
}
