package br.com.cuidebem.controller.email;

import java.text.MessageFormat;
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
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ " <meta charset=\"UTF-8\">"
					+ "</head>"
					+ "<body>"
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
					+ "} "
					+ ".ui-panel-titlebar {text-align: center;} "
					+"*,*::before,*::after {margin: 0;padding: 0;box-sizing: border-box;} "
					+ ".timeline ul {background: #C2D5D3;padding: 50px 0;} "
					+ ".timeline ul li {list-style-type: none;position: relative;width: 6px;margin: 0 auto;padding-top: 50px;background: #fff;} "
					+ ".ui-btn-timeline {content: '';position: absolute;left: 50%;bottom: 0;transform: translateX(-50%);width: 30px;height: 30px;border-radius: 50%;background: inherit;} "
					+ ".timeline ul li div {position: relative;bottom: 0;width: 200px;padding: 15px;background: #46A7A3;} "
					+ ".timeline ul li div::before {content: '';position: absolute;bottom: 7px;width: 0;height: 0;border-style: solid;} "
					+ ".timeline ul li:nth-child(odd) div {left: 45px;} "
					+ ".timeline ul li:nth-child(odd) div::before {left: -15px;border-width: 8px 16px 8px 0;border-color: transparent #46A7A3 transparent transparent;} "
					+ ".timeline ul li:nth-child(even) div {left: -239px;} "
					+ ".timeline ul li:nth-child(even) div::before {right: -15px;border-width: 8px 0 8px 16px;border-color: transparent transparent transparent #46A7A3;} "
					+ "time {display: block;font-size: 1.2rem;font-weight: bold;margin-bottom: 8px;} "
					+ ".timeline ul li::after {transition: background .5s ease-in-out;} "
					+ ".ui-btn-timeline {background: #46A7A3 !important;border-color: #46A7A3 !important;} "
					+ ".timeline ul li div {visibility: hidden;opacity: 0;transition: all .5s ease-in-out;} "
					+ ".timeline ul li:nth-child(odd) div {transform: translate3d(200px, 0, 0);} "
					+ ".timeline ul li:nth-child(even) div {transform: translate3d(-200px, 0, 0);} "
					+ ".timeline ul li.in-view div {transform: none;visibility: visible;opacity: 1;} "
					+ "@media screen and (max-width: 900px) {.timeline ul li div {width: 150px;} "
					+ ".timeline ul li:nth-child(even) div {left: -189px;/*250+45-6*/}}"
					+ "@media screen and (max-width: 600px) {.timeline ul li {margin-left: 20px;}.timeline ul li div {width: calc(100vw - 91px);}.timeline ul li:nth-child(even) div {left: 45px;} "
					+ ".timeline ul li:nth-child(even) div::before {left: -15px;border-width: 8px 16px 8px 0;border-color: transparent #46A7A3 transparent transparent;}} "
					+ "</style>"
					+ "<div class=\"banner-title ui-widget-header\">"
					+ "<div class=\"ui-corner-all\">"
					+ "<div class=\"ui-g\">"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-2\">"
					+ "<img src=\"http://cuidador.cuidebemapp.com/resources/img/titulo25.png\" style=\"width: 100%; height: auto\"></div>"
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
							+ "<img  src=\"http://cuidador.cuidebemapp.com/rs/images/paciente/{0}\" style=\"width: 100%; height: auto\">"
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
	private static String footer = "</div></div></div>"
			+"<link type=\"text/css\" rel=\"stylesheet\" href=\"http://cuidador.cuidebemapp.com/javax.faces.resource/theme.css.xhtml?ln=primefaces-hot-sneaks\"></link>"
			+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://cuidador.cuidebemapp.com/javax.faces.resource/components.css.xhtml?ln=primefaces&v=6.1\"></link>"
			+ "<script type=\"text/javascript\">"
			+ "	(function() {'use strict';var items = document.querySelectorAll(\".timeline li\");"
			+ "function isElementInViewport(el) {var rect = el.getBoundingClientRect();return (rect.top >= 0 && rect.left >= 0 && "
			+ "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && rect.right <= (window.innerWidth || document.documentElement.clientWidth));}"
			+ "function callbackFunc() {for (var i = 0; i < items.length; i++) {if (isElementInViewport(items[i])) {items[i].classList.add(\"in-view\");}}}})();"
			+ "</script>"
			+ "</body></html>";	
	private static String content_type = "text/html;charset=UTF-8";
	private static String subject_email = "Relatório diário de eventos - {0}";
	
	
	public void sendMessage(EventoEmailModel model){
		String content = mountContent(model);
		String subject = MessageFormat.format(subject_email, model.getResidencia());
	
		for(Responsavel responsavel : model.getResponsaveis()){
			sendEmail.send(responsavel.getEmail(), subject, content, content_type);
		}
		
	}
	

	
	public String mountContent(EventoEmailModel model){
		String _panelFoto = mountPanelFoto(model.getData(), model.getPaciente(), model.getIdpaciente());
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
	
	private String mountPanelFoto(Date data, String  paciente, Integer idpaciente ){
		String _panelfoto = null;
		String _foto = MessageFormat.format(foto, idpaciente);
		String date = DateUtil.convertDate(data);
		_panelfoto = MessageFormat.format(panelfoto, date,paciente,_foto);
		return _panelfoto;
	}
}
