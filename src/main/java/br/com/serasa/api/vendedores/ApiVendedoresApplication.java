package br.com.serasa.api.vendedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApiVendedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVendedoresApplication.class, args);
	}

}
