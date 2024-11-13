package com.br.csi.gda;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Gerenciamento de Abrigos",
				version = "0.7",
				description = "Documentação do APP de gerenciamento de abrigos",
				contact = @Contact(name = "Suporte", email = "nãoémeuemail@gmail.com")
		)
)
@SpringBootApplication
public class GerenciamentoDeAbrigosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoDeAbrigosApplication.class, args);
	}

}
