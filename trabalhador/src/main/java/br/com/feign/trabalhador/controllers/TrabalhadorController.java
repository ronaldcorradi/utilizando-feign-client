package br.com.feign.trabalhador.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.feign.trabalhador.entities.Trabalhador;
import br.com.feign.trabalhador.services.TrabalhadorService;


@RestController
@RequestMapping("/trabalhador")
public class TrabalhadorController {
	
	@Autowired
	private TrabalhadorService trabalhadorService;
	
	@GetMapping
	public ResponseEntity<List<Trabalhador>> getAll(){
		return ResponseEntity.ok(trabalhadorService.getAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Trabalhador> getById(@PathVariable(name = "id") Long id){
		Optional<Trabalhador> trabalhador = trabalhadorService.getById(id);
		if(trabalhador.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(trabalhadorService.getById(id).get());
	}

}
