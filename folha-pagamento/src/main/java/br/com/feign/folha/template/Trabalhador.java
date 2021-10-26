package br.com.feign.folha.template;

public class Trabalhador {
	
	private Long id;	
	private String nome;
	private Double valorHoraTrabalhada;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorHoraTrabalhada() {
		return valorHoraTrabalhada;
	}
	public void setValorHoraTrabalhada(Double valorHoraTrabalhada) {
		this.valorHoraTrabalhada = valorHoraTrabalhada;
	}

	
}
