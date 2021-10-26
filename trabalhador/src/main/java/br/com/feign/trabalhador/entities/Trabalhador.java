package br.com.feign.trabalhador.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trabalhador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;	
	@Column(name = "valor_hora")
	private Double valorHoraTrabalhada;
	
	public Trabalhador() {
	}
	
	public Trabalhador(String nome, Double valorHoraTrabalhada) {
		this.nome = nome;
		this.valorHoraTrabalhada = valorHoraTrabalhada;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Double getValorHoraTrabalhada() {
		return valorHoraTrabalhada;
	}	

}
