package br.com.fiap.soat.pagamentos.config;

import br.com.fiap.soat.pagamentos.controllers.PagamentoController;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = {TestConfig.class, UseCaseBeanConfig.class, ControllerBeanConfig.class })
public class CucumberSpringConfiguration {
    public CucumberSpringConfiguration() {
        System.out.println("CucumberSpringConfiguration Context Loaded!");
    }
}