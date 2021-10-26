package br.com.feign.folha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FolhaPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FolhaPagamentoApplication.class, args);
	}

}
