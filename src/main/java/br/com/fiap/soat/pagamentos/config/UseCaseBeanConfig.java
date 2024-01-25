package br.com.fiap.soat.pagamentos.config;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.*;
import br.com.fiap.soat.pagamentos.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {
    @Bean
    public CriarPagamentoUseCasePort criarPagamentoUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new CriarPagamentoUseCase(pagamentoGatewayPort);
    }
    @Bean
    public ObterPagamentoPorIdUseCasePort obterPagamentoPorIdUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new ObterPagamentoPorIdUseCase(pagamentoGatewayPort);
    }

    @Bean
    public ReceberConfirmacaoPagamentoUseCasePort ReceberConfirmacaoPagamentoUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new ReceberConfirmacaoPagamentoUseCase(pagamentoGatewayPort);
    }

    @Bean
    public ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCasePort(PagamentosGatewayPort pagamentoGatewayPort) {
        return new ObterPagamentosPorStatusUseCase(pagamentoGatewayPort);
    }
}
