package br.com.fiap.soat.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.fiap.soat.pagamentos")
public class PagamentosApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(PagamentosApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}