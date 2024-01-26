package br.com.fiap.soat.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
public class PagamentosApplication {
	public static void main(String[] args) {
		SpringApplication.run(PagamentosApplication.class, args);
	}
}
