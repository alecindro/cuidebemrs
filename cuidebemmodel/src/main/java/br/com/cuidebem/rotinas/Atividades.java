package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Atividades implements IRotina{
	
	ARTERAPIA("Arterapia","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}},
	ESTIMULACAOCOGNITIVA("Estimulação Cognitiva","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}},
	MASSAGEM("Massagem","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}},
	DANCA("Dança","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}},
	ATIVIDADEFISICA("Atividade Física","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}},
	ATIVIDADESLUDICAS("Atividades Lúdicas","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}},
	OUTRAS("Outras","/app/evento/atividades.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}};
	
	private String descricao;
	private String page;
	public abstract void genResumo(Evento evento);
	
	

	private Atividades(String descricao, String page) {
		this.descricao = descricao;
		this.page = page;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public IRotina fromDescricao(String descricao) {
		for (Atividades atividades : Atividades.values()) {
			if (atividades.getDescricao().equals(descricao)) {
				return atividades;
			}
		}
		return null;
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Atividades atividades : Atividades.values()) {
			descricaos.add(atividades.getDescricao());
		}
		return descricaos;
	}

	
}
