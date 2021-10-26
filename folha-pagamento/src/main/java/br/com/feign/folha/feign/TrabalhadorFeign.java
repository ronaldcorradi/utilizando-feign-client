package br.com.feign.folha.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.feign.folha.template.Trabalhador;

@Component
@FeignClient(name = "trabalhador",url = "http://localhost:8001", path ="/trabalhador/id/")
public interface TrabalhadorFeign {
	
	@GetMapping("{id}")
	ResponseEntity<Trabalhador> getById(@PathVariable ("id")Long id);

}
