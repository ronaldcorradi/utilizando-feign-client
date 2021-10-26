package br.com.feign.folha.model;

import br.com.feign.folha.template.Trabalhador;

public class FolhaPagamento {
	
	private Trabalhador trabalhador;
	private int diasTrabalhados;
	
		
	public Trabalhador getTrabalhador() {
		return trabalhador;
	}
	public void setTrabalhador(Trabalhador trabalhador) {
		this.trabalhador = trabalhador;
	}
	public int getDiasTrabalhados() {
		return diasTrabalhados;
	}
	public void setDiasTrabalhados(int diasTrabalhados) {
		this.diasTrabalhados = diasTrabalhados;
	}
	
	public Double getTotal() {
		return this.diasTrabalhados * this.trabalhador.getValorHoraTrabalhada();
	}

	
	

}
