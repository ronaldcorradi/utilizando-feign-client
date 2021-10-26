package br.com.feign.folha.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.feign.folha.model.FolhaPagamento;
import br.com.feign.folha.services.FolhaPagamentoService;
import br.com.feign.folha.template.Trabalhador;

@RestController
@RequestMapping("/folha")
public class FolhaPagamentoController {

	@Autowired
	private FolhaPagamentoService folhaPagamentoService;

	@GetMapping("/trabalhador-id/{trabalhador}")
	public ResponseEntity<Trabalhador> getTrabalhador(@PathVariable("trabalhador") Long id) {
		Optional<Trabalhador> trabalhador = folhaPagamentoService.getTrabalhadorById(id);
		if(trabalhador.isPresent()) {
			return ResponseEntity.ok(trabalhador.get());			
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/trabalhador-id/{trabalhador}/dias/{dias}")
	public ResponseEntity<FolhaPagamento> getCalcular(@PathVariable("trabalhador") Long id,
			@PathVariable("dias") int dias) {
		FolhaPagamento folhaPagamento = new FolhaPagamento();
		Optional<Trabalhador> trabalhador = folhaPagamentoService.getTrabalhadorById(id);
		if(trabalhador.isPresent()) {
			folhaPagamento.setDiasTrabalhados(dias);
			folhaPagamento.setTrabalhador(trabalhador.get());
			return ResponseEntity.ok(folhaPagamento);		
		}
		return ResponseEntity.notFound().build();	

	}

}
