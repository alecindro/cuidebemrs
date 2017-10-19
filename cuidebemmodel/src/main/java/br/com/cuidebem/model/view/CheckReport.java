package br.com.cuidebem.model.view;

import java.util.Date;

public class CheckReport {
	
	private Date entrada;
	private Date saida;
	private String tempo;
	private String nomeCuidador;
	private String obsEntrada;
	private String obsSaida;
	public Date getEntrada() {
		return entrada;
	}
	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}
	public Date getSaida() {
		return saida;
	}
	public void setSaida(Date saida) {
		this.saida = saida;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getNomeCuidador() {
		return nomeCuidador;
	}
	public void setNomeCuidador(String nomeCuidador) {
		this.nomeCuidador = nomeCuidador;
	}
	public String getObsEntrada() {
		return obsEntrada;
	}
	public void setObsEntrada(String obsEntrada) {
		this.obsEntrada = obsEntrada;
	}
	public String getObsSaida() {
		return obsSaida;
	}
	public void setObsSaida(String obsSaida) {
		this.obsSaida = obsSaida;
	}
	@Override
	public String toString() {
		return "CheckReport [entrada=" + entrada + ", saida=" + saida + ", tempo=" + tempo + "]";
	}
	
	
	
	

}
