package br.com.cuidebem.controller.email;

import java.text.MessageFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.SendEmail;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Responsavel;

@Stateless
public class EventoResponsavel {

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
					+ "</style>"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://lar.cuidebemapp.com/resources/css/timeline.css\" ></link>"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://lar.cuidebemapp.com/javax.faces.resource/components.css.xhtml?ln=primefaces&v=6.1\"></link>"
					+ "<script type=\"text/javascript\" src=\"http://lar.cuidebemapp.com/resources/js/timeline.js\"></script>"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ " <meta charset=\"UTF-8\">"
					+ "</head>"
					+ "<body>"
					+ "<div class=\"banner-title\">"
					+ "<div class=\"ui-corner-all\">"
					+ "<div class=\"ui-g\">"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-2\">"
					+ "<img src=\"http://lar.cuidebemapp.com/resources/img/titulo25.png\" style=\"width: 100%; height: auto\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-4\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"><p>Aplicação voltada para o bem estar dos nossos entes queridos</p></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-3\"></div>"
					+ "</div></div></div>"
					+ "<div class=\"ui-g\">"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-2\">"
					+ "<img  src=\"http://lar.cuidebemapp.com/rs/images/paciente/#{1}\" alt=\"Foto da #{2}\" style=\"width: 100%; height: auto\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-4\"></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-5\">"
					+ "<h1> Eventos da Paciente #{3}</h1></div>"
					+ "<div class=\"ui-g-4 ui-md-4 ui-lg-3\"></div></div>"
					+"<section class=\"timeline\"><ul>";
	
	private static String timeline = "<li class=\"in-view\">"
			+ "<div>"
			+ "<time>#{1}</time>#{2}<br>#{3}<br> #{4}<br>#{5}<br> Observação : #{6} Cuidador : #{7} <br></div> "
			+ "<span class=\"ui-btn-timeline\"> </span></li>";
	
	private static String footer = "</ul></section></body></html>";	
	private static String content_type = "text/html;charset=UTF-8";
	private static final String subject_email = "Relatório diário de eventos - CuideBem APP";
	
	
	public void sendMessage(List<Responsavel> responsaveis,List<Evento> eventos,Paciente paciente){
		String content = mountContent(paciente, eventos);
		for(Responsavel responsavel : responsaveis){
			sendEmail.send(responsavel.getEmail(), subject_email, content, content_type);
		}
		
	}
	
	private String mountContent(Paciente paciente, List<Evento> eventos){
		String content = MessageFormat.format(header, paciente.getIdpaciente(),paciente.getApelido(),paciente.getApelido());
		String _timeline = "";
		for(Evento evento : eventos){
			 _timeline = _timeline.concat(MessageFormat.format(timeline, evento.getHour(),evento.getData(),evento.getGrupoevento(),evento.getSubgrupoevento(),evento.getRespeventos(),evento.getObsevento(), evento.getUsuario().getApelido()));
		 }
		content = content.concat(timeline).concat(footer);
		return content;
	}
}
