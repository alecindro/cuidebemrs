package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Respiratorio implements IRotina{

	TRAQUEAL("Aspiração Traqueal",Arrays.asList("Oral","Nasal"),Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Seroide","Mucosa","Purulenta"),"/app/evento/traqueal.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				"Descrição: " + evento.getDescricao() + " - Quantidade: " + evento.getQuantidade() + " - Aspecto: " + evento.getAspecto());
	}},
	NEBULIZACAO("Nebulização",Arrays.asList("Oral","Nasal"),Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Seroide","Mucosa","Purulenta"),"/app/evento/nebulizacao.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				"Descrição: " + evento.getDescricao() + " - Quantidade: " + evento.getQuantidade() + " - Aspecto: " + evento.getAspecto());
	}};
	
	private String descricao;
	private List<String> opcoes;
	private List<String> quantidade;
	private List<String> aspecto;
	private String page;
	public abstract void genResumo(Evento evento);
	
	private Respiratorio(String descricao, List<String> opcoes, List<String> quantidade, List<String> aspecto,String page) {
		this.descricao = descricao;
		this.opcoes = opcoes;
		this.quantidade = quantidade;
		this.aspecto = aspecto;
		this.page = page;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getOpcoes() {
		return opcoes;
	}
	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	public List<String> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(List<String> quantidade) {
		this.quantidade = quantidade;
	}
	public List<String> getAspecto() {
		return aspecto;
	}
	public void setAspecto(List<String> aspecto) {
		this.aspecto = aspecto;
	}
	
	
	
	public String getPage() {
		return page;
	}
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Respiratorio respiratorio : Respiratorio.values()) {
			descricaos.add(respiratorio.getDescricao());
		}
		return descricaos;
	}
		
	
	@Override
	public IRotina fromDescricao(String descricao) {
		for (Respiratorio _enum : Respiratorio.values()) {
			if (_enum.getDescricao().equals(descricao)) {
				return _enum;
			}
		}
		return null;
	}
	
	
}
