package br.com.cuidebem.controller.email;

import java.text.MessageFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.SendEmail;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.translate.Bundle;

@Stateless
public class EventoEmail {

	@EJB 
	private SendEmail sendEmail;
	
	private static String header = "<html>"
			+ "<head>"
			+ "<style type=\"text/css\">"
					+ "body {"
					+ "margin: 0px;"
					+ "height: 100%;"
					+ "overflow-x: hidden;"
					+ "overflow-y: auto;"
					+ "background-color: #20272a;"
					+ "font-family: monospace, 'latoregular', \"Trebuchet MS\", Arial, Helvetica,sans-serif;"
					+ "color: #5C666A;"
					+ "font-size: 14px;"
					+ "}"
					+ ".ui-panel-titlebar {text-align: center;}"
					+ "</style>"
					+ "<link type=\"text/css\" rel=\"stylesheet\" href=\"http://cuidador.cuidebemapp.com/javax.faces.resource/theme.css.xhtml?ln=primefaces-hot-sneaks\"></link>"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://cuidador.cuidebemapp.com/resources/css/timeline.css\" ></link>"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://cuidador.cuidebemapp.com/javax.faces.resource/components.css.xhtml?ln=primefaces&v=6.1\"></link>"
					+ "<script type=\"text/javascript\" src=\"http://lar.cuidebemapp.com/resources/js/timeline.js\"></script>"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ " <meta charset=\"UTF-8\">"
					+ "</head>"
					+ "<body>"
					+ "<div class=\"banner-title ui-widget-header\">"
					+ "<div class=\"ui-corner-all\">"
					+ "<div class=\"ui-g\">"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-2\">"
					+ "<img src=\"http://lar.cuidebemapp.com/resources/img/titulo25.png\" style=\"width: 100%; height: auto\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-1 ui-md-1 ui-lg-4\"></div>"
					+ "<div class=\"ui-g-10 ui-md-10 ui-lg-5\"><p>Aplicação voltada para o bem estar dos nossos entes queridos</p></div>"
					+ "<div class=\"ui-g-1 ui-md-1 ui-lg-3\"></div>"
					+ "</div></div></div>"+
					"<div class=\"ui-g\">";
					private static String panelfoto = "<div class=\"ui-g-12 ui-md-12 ui-lg-12\">"
					+"<div class=\"ui-panel ui-widget ui-widget-content ui-corner-all\" style=\"width:100%\" data-widget=\"widget_title\">"
					+"<div id=\"title_header\" class=\"ui-panel-titlebar ui-widget-header ui-helper-clearfix ui-corner-all\">"
					+"<span class=\"ui-panel-title\">Diário do dia {0} - {1}</span>"
					+ "</div>"
					+ "<div class=\"ui-panel-content ui-widget-content\"> {2}"
					+ "</div>"
					+ "</div>"
					+ "</div>";
					
					private static String foto= "<div class=\"ui-g\">"
							+"<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
							+"<div class=\"ui-g-4 ui-md-4 ui-lg-2\">"					
							+ "<img  src=\"data:image/png;base64,{0}\" alt=\"Foto da {1}\" style=\"width: 100%; height: auto\">"
							+ "</div>"
							+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
							+ "</div>";
							
	
	private static String memo = "	<div class=\"ui-g-12 ui-md-12 ui-lg-12\">"
			+ "<div id=\"memo\" class=\"ui-panel ui-widget ui-widget-content ui-corner-all\" "
			+ "style=\"width:100%\" data-widget=\"widget_memo\">"
			+ "<div id=\"memo_header\" class=\"ui-panel-titlebar ui-widget-header ui-helper-clearfix ui-corner-all\">"
			+ "<span class=\"ui-panel-title\">Anotação</span>"
			+ "</div>"
			+ "<div id=\"memo_content\" class=\"ui-panel-content ui-widget-content\">"
			+ "{0}"
			+ "</div>"
			+ "</div>"
			+ "</div>";
	private static String panelTimeline = "<div class=\"ui-g-12 ui-md-12 ui-lg-12\">"
			+ "<div id=\"timeline\" class=\"ui-panel ui-widget ui-widget-content ui-corner-all\" "
			+ "style=\"width:100%\" data-widget=\"widget_timeline\">"
			+ "<div id=\"timeline_header\" class=\"ui-panel-titlebar ui-widget-header ui-helper-clearfix ui-corner-all\">"
			+ "<span class=\"ui-panel-title\">Timeline</span>"
			+ "</div>"
			+ "<div id=\"timeline_content\" class=\"ui-panel-content ui-widget-content\">";
	
	private static String endPanelTimeline = "</div>";
	
	private static String initTimeline =  "<section class=\"timeline\">"
			+ "<ul>";
	
	private static String timeline = "<li class=\"in-view\">"
			+ "<div>"
			+ "<time>{0}</time>{1}<br>{2}<br> {3}<br>{4}<br> Observação : {5} Cuidador : {6} <br></div> "
			+ "<span class=\"ui-btn-timeline\"> </span></li>";
	private static String endTimeline = "</ul></section>";
	private static String footer = "</div></div></div></body></html>";	
	private static String content_type = "text/html;charset=UTF-8";
	private static String subject_email = "Relatório diário de eventos - {0}";
	
	
	public void sendMessage(EventoEmailModel model){
		String content = mountContent(model);
		String subject = MessageFormat.format(subject_email, model.getResidencia());
		String to_email = "";
		for(Responsavel responsavel : model.getResponsaveis()){
			to_email.concat(responsavel.getEmail()).concat(";");
		}
		sendEmail.send(to_email, subject, content, content_type);
	}
	
	public String mountContent(EventoEmailModel model){
		String _panelFoto = mountPanelFoto(model.getData(), model.getPaciente(), model.getPhoto());
		String _memorando = mountMemo(model.getMemorando());
		String _timeline = mountTimeline(model.getEventos());
		String content = header.concat(_panelFoto).concat(_memorando).concat(_timeline).concat(footer);
		return content;
	}
	
	private String mountTimeline(List<Evento> eventos){
		String _timeline = panelTimeline;
		
		if(eventos.isEmpty()){
			 _timeline = _timeline.concat(Bundle.getValue("no_register"));
			 return _timeline.concat(endPanelTimeline);
		}
		_timeline = _timeline.concat(initTimeline);
		for(Evento evento : eventos){
			 _timeline = _timeline.concat(MessageFormat.format(timeline, evento.getHour(),evento.getData(),evento.getGrupoevento(),evento.getSubgrupoevento(),evento.getRespeventos(),evento.getObsevento(), evento.getUsuario().getApelido()));
		 }
		_timeline = _timeline.concat(endTimeline).concat(endPanelTimeline);
		return _timeline;
	}
	
	private String mountMemo(String memorando){
		if(memorando == null || memorando.trim().length() == 0){
			memorando = Bundle.getValue("no_memorando");
		}
		return MessageFormat.format(memo,memorando);
	}
	
	private String mountPanelFoto(Date data, String  paciente, byte[] photo ){
		String _foto = null;
		String _panelfoto = null;
		if(photo != null){
			String foto64 = Base64.getEncoder().encodeToString(photo);
			_foto = MessageFormat.format(foto, foto64);
		}
		String date = DateUtil.convertDate(data);
		if(_foto!= null){
			_panelfoto = MessageFormat.format(panelfoto, date,paciente,_foto);
		} else{
			_panelfoto = MessageFormat.format(panelfoto, date, paciente," ");
		}
		return _panelfoto;
	}
}
