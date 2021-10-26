package br.com.feign.trabalhador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.feign.trabalhador.entities.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador,Long>{

}
