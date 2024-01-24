package br.com.fiap.soat.pagamentos.config;

import br.com.fiap.soat.pagamentos.controllers.PagamentoController;
import br.com.fiap.soat.pagamentos.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeanConfig {
    @Bean
    public PagamentoController pagamentoController(ObterPagamentoPorIdUseCasePort obterPagamentoPorIdUseCase,
                                                   ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase,
                                                   ObterPagamentosPorStatusUseCasePort obterPagamentosPorStatusUseCase,
                                                   CriarPagamentoUseCasePort criarPagamentoUseCase) {
        return new PagamentoController(obterPagamentoPorIdUseCase, receberConfirmacaoPagamentoUseCase, obterPagamentosPorStatusUseCase, criarPagamentoUseCase);
    }
}
