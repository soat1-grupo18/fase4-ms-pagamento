package br.com.fiap.soat.pagamentos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"br.com.fiap.soat.pagamentos", "br.com.fiap.soat.pagamentos.acceptance.steps"})
@TestConfiguration
@Profile("test")
public class TestConfig {
    public TestConfig() {
        System.out.println("Spring Test Context Loaded!");
    }

    @Bean
    public DataSource dataSource() {
        // Configure and return H2 DataSource for tests
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
