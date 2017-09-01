package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Nutricao implements IRotina{

	CAFE("Café da manhã",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou"),"/app/evento/cafe.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getDescricao() );
	}},
	ALMOCO("Almoço",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou"),"/app/evento/almoco.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getDescricao() );
	}},
	JANTAR("Jantar",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou"),"/app/evento/jantar.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getDescricao() );
	}},
	CEIA("Ceia",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou"),"/app/evento/ceia.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getDescricao() );
	}},
	LANCHE("Lanche",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou"),"/app/evento/lanche.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getDescricao() );
	}},
	LIQUIDOS("Ingestão de Líquidos",Arrays.asList("Pouco","Moderada","Grande"),"/app/evento/liquidos.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getDescricao() );
	}};
	
	private String descricao;
	private List<String> opcoes;
	private String page;
	public abstract void genResumo(Evento evento);
	
	private Nutricao(String descricao, List<String> opcoes, String page) {
		this.descricao = descricao;
		this.opcoes = opcoes;
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
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Nutricao nutricao : Nutricao.values()) {
			descricaos.add(nutricao.getDescricao());
		}
		return descricaos;
	}
	
	@Override
	public IRotina fromDescricao(String descricao) {
		for (Nutricao _enum : Nutricao.values()) {
			if (_enum.getDescricao().equals(descricao)) {
				return _enum;
			}
		}
		return null;
	}

	@Override
	public String getPage() {
		return page;
	}
	
}
