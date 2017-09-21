package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Dor implements IRotina{

	DOR(" --- ","/app/evento/blank.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos(
				evento.getObsevento());
	}};
	
	private String descricao;
	private String page;
	public abstract void genResumo(Evento evento);
	
	private Dor(String descricao, String page) {
		this.descricao = descricao;
		this.page = page;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Dor dor : Dor.values()) {
			descricaos.add(dor.getDescricao());
		}
		return descricaos;
	}
	
	@Override
	public IRotina fromDescricao(String descricao) {
		for (Dor _enum : Dor.values()) {
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
