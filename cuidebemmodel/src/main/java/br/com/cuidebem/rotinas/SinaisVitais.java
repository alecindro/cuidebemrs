package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum SinaisVitais implements IRotina{

	PRESSAOARTERIAL("Pressão Arterial","/app/evento/pressaoarterial.xhtml"){
		public void genResumo(Evento evento){
		evento.setRespeventos(evento.getPressaoInicial() + " x " + evento.getPressaoFinal() + " mmHg");
	} },
	FREQUENCIACARDIACA("Frequência Cardíaca","/app/evento/frequenciacardiaca.xhtml"){
		public void genResumo(Evento evento){
			evento.setRespeventos(evento.getValue() + " bpm");
		} },
	TEMPERATURA("Temperatura Corporal","/app/evento/temperatura.xhtml"){
			public void genResumo(Evento evento){
				evento.setRespeventos(evento.getValue()+ " ºC");
			} },
	FREQUENCIARESPIRATORIA("Frequência Respiratória","/app/evento/frequenciarespiratoria.xhtml"){
		public void genResumo(Evento evento){
			evento.setRespeventos(evento.getValue() + " vezes/min");
		} },
	SATURACAOOXIGENIO("Saturação de Oxigênio (oximetria)","/app/evento/oximetria.xhtml"){
			public void genResumo(Evento evento){
				evento.setRespeventos(evento.getValue() + " %");
			} },
	CONTROLEGLICEMIA("Controle da Glicemia","/app/evento/glicemia.xhtml"){
		public void genResumo(Evento evento){
			evento.setRespeventos(evento.getValue() + " mmHg");
		} };
	
private String descricao;
private String page;
public abstract void genResumo(Evento evento);
	
	private SinaisVitais(String descricao,String page) {
		this.descricao = descricao;
		this.page = page;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
	public SinaisVitais[] getValues(){
		return SinaisVitais.values();
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (SinaisVitais sinaisVitais : SinaisVitais.values()) {
			descricaos.add(sinaisVitais.getDescricao());
		}
		return descricaos;
	}
	
	@Override
	public String getPage() {
		return page;
	}


	@Override
	public IRotina fromDescricao(String descricao) {
		for (SinaisVitais sinalVital : SinaisVitais.values()) {
			if (sinalVital.getDescricao().equals(descricao)) {
				return sinalVital;
			}
		}
		return null;
	}
}
