package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BemEstar{

	ACORDAR("Sono - Acordou",Arrays.asList("Dormiu bem","Agitou","Insônia")),
	DORMIR("Sono - Dormiu",Arrays.asList("Normal","A base de remédios")),
	PASSEIOS("Passeios",Arrays.asList("Iniciou","Retornou")),
	CONSCIENCIA("Nível de Consciência",Arrays.asList("Acordado", "Sonolento","Irresponsivo")),
	HUMOR("Humor",Arrays.asList("Alegre","Calmo","Apático","Depressivo","Agressivo","Agitado"));
	
	private String descricao;
	private List<String> opcoes;

	private BemEstar(String descricao, List<String> opcoes) {
		this.descricao = descricao;
		this.opcoes = opcoes;
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
		for (BemEstar bemEstar : BemEstar.values()) {
			descricaos.add(bemEstar.getDescricao());
		}
		return descricaos;
	}
	
	
}
