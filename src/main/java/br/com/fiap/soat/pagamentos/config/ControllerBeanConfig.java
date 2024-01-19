package br.com.fiap.soat.pagamentos.config;

import br.com.fiap.soat.pagamentos.controllers.PagamentoController;
import br.com.fiap.soat.pagamentos.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeanConfig {
    @Bean
    public PagamentoController pagamentoController(ConsultarStatusUseCasePort consultarStatusUseCase,
                                                   ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase) {
        return new PagamentoController(consultarStatusUseCase, receberConfirmacaoPagamentoUseCase);
    }
}
