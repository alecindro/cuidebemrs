package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Eliminacoes implements IRotina{

	URINA("Urina",Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Clara","Escura","Avermelhada"),"/app/evento/urina.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos("Quantidade: " + evento.getQuantidade() + " - Aspecto: " + evento.getAspecto());
	}},
	FEZES("Fezes",Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Líquido","Pastoso","Endurecido","Fecaloma,Sangue"),"/app/evento/fezes.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos("Quantidade: " + evento.getQuantidade() + " - Aspecto: " + evento.getAspecto());
	}},
	VOMITO("Vomito",Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Restos Alimentares","Amarelo","Sangue","Borra de Café"),"/app/evento/vomito.xhtml")
	{public void genResumo(Evento evento){
		evento.setRespeventos("Quantidade: " + evento.getQuantidade() + " - Aspecto: " + evento.getAspecto());
	}};
	
	private String page;
	private String descricao;
	private List<String> quantidade;
	private List<String> aspecto;
	public abstract void genResumo(Evento evento);
	private Eliminacoes(String descricao, List<String> quantidade, List<String> aspecto,String page) {
		this.descricao = descricao;
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
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Eliminacoes eliminacoes : Eliminacoes.values()) {
			descricaos.add(eliminacoes.getDescricao());
		}
		return descricaos;
	}
	@Override
	public IRotina fromDescricao(String descricao) {
		for (Eliminacoes _enum : Eliminacoes.values()) {
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
