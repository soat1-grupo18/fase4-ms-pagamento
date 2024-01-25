package br.com.fiap.soat.pagamentos.usecases;

import br.com.fiap.soat.pagamentos.entities.Pagamento;
import br.com.fiap.soat.pagamentos.interfaces.gateways.PagamentosGatewayPort;
import br.com.fiap.soat.pagamentos.interfaces.usecases.CriarPagamentoUseCasePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Profile("!test")
public class CriarPagamentoUseCase implements CriarPagamentoUseCasePort {

    private final PagamentosGatewayPort pagamentoGateway;

    public CriarPagamentoUseCase(PagamentosGatewayPort pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pagamento execute(Pagamento pagamento) {
        return pagamentoGateway.criarPagamento(pagamento);
    }

    @Service
    @Profile("test") // Activate this bean only when the 'test' profile is active
    public static class TestConfig {

        private final DataSource dataSource;

        @Autowired
        public TestConfig(@Qualifier("dataSource") DataSource dataSource) {
            System.out.println("Using H2 Database.");
            this.dataSource = dataSource;
        }
    }
}

