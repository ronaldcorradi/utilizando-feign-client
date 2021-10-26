package br.com.feign.trabalhador.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.feign.trabalhador.entities.Trabalhador;
import br.com.feign.trabalhador.repositories.TrabalhadorRepository;

@Service
public class TrabalhadorService {

	@Autowired
	private TrabalhadorRepository trabalhadorRepository;
	
	public Optional<Trabalhador> getById(Long id){
		return trabalhadorRepository.findById(id);
	}
	
	public List<Trabalhador> getAll(){
		return trabalhadorRepository.findAll();
	}
	
	
}
