package br.com.cuidebem.rotinas;

import br.com.cuidebem.model.Evento;

public interface IRotina {

	
	public String getPage();
	
	public IRotina fromDescricao(String descricao);
	public String genResumo(Evento evento);
}
