package br.com.feign.folha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.feign.folha.feign.TrabalhadorFeign;
import br.com.feign.folha.template.Trabalhador;

@Service
public class FolhaPagamentoService {
	
	@Autowired
	private TrabalhadorFeign trabalhadorFeign;
	
	public Optional<Trabalhador> getTrabalhadorById(Long id) {
		Trabalhador trabalhador = trabalhadorFeign.getById(id).getBody();
		if(trabalhador != null) {
			return Optional.of(trabalhador);			
		}
		return Optional.empty();
	}

}
