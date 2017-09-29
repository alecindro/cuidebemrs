package br.com.cuidebem.controller.email;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.SendEmail;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.translate.Bundle;

@Stateless
public class EventoMail {

	@EJB
	private SendEmail sendEmail;

	private final static String header = "<div style=\"display: flex;flex-wrap: wrap;\"><div style=\"width:100%;\">";
	private final static String footer = "</div></div>";
	private final static String headerpanelfoto = "<div  style=\"padding: .2em;border-radius: 3px;border: 1px solid #262626;color: #dddddd;background-color: #4d4d4d;background: -webkit-radial-gradient(50% 75%,circle,#676767,#343434);\">"
			+ "<div  style=\"padding: .5em 1em .3em;text-align: center;font-weight: normal;border-radius: 3px;background: linear-gradient(#99a699,#778877);outline: 0;border: 1px solid #3c443c;color: #eeffee;\">"
			+ "<span style=\"margin: .1em 16px .2em 0;\">" + "Diário do dia {0} - {1}</span></div>";
	private final static String contentpanelfoto = "<div  style=\"border: 0; background: 0; padding: .5em 1em; color: #dddddd; text-align:center\"><img src=\"http://cuidador.cuidebemapp.com/rs/images/paciente/{0}\" style=\"height: auto\"></img>";
	private final static String footerpanel = "</div></div>";
	private final static String panelAnotacao = "<div  style=\"padding: .2em;border-radius: 3px;border: 1px solid #262626;color: #dddddd;background-color: #4d4d4d;background: -webkit-radial-gradient(50% 75%,circle,#676767,#343434);\">"
			+ "<div  style=\"padding: .5em 1em .3em;text-align: center;font-weight: normal;border-radius: 3px;background: linear-gradient(#99a699,#778877);outline: 0;border: 1px solid #3c443c;color: #eeffee;\">"
			+ "<span style=\"margin: .1em 16px .2em 0;\">Anotações</span></div><div  style=\"border: 0;background: 0;padding: .5em 1em;color: #dddddd; \">{0}";
	private final static String headerpanelEvento = "<div  style=\"padding: .2em;border-radius: 3px;border: 1px solid #262626;color: #dddddd;background-color: #4d4d4d;background: -webkit-radial-gradient(50% 75%,circle,#676767,#343434);\">"
			+ "<div  style=\"padding: .5em 1em .3em;text-align: center;font-weight: normal;border-radius: 3px;background: linear-gradient(#99a699,#778877);outline: 0;border: 1px solid #3c443c;color: #eeffee;\">"
			+ "<span style=\"margin: .1em 16px .2em 0;\">Timeline</span></div>"
			+ "<div  style=\"border: 0;background: 0;padding: .5em 1em;color: #dddddd; \">";
	private final static String tablepanelEvento = "<table align=\"center\" style=\"font-family: monospace, 'latoregular', Trebuchet MS, Arial, Helvetica, sans-serif;font-size: 14px; background: #C2D5D3;\">";
	private final static String leftRow = "<tr><td style=\"width:90%; border:1px solid #46A7A3;background:#46A7A3;color:#dddddd;float:right\">"
			+ "<time style=\"font-size: 1.2rem;display: block;font-weight: bold;margin-bottom: 8px;\">{0}</time>"
			+ "{1} {2}  {3} {4} {5}</td>" + "<td style=\"text-align:center;width:20%\">" + "</td><td></td></tr>";
	private final static String centerRow = "<tr style=\"height: 30px\"><td></td><td style=\"vertical-align: baseline;text-align:center;width:20%\">"
			+ "<span class=\"ui-btn-timeline\" style=\"content: '';position: absolute;transform: translateX(-50%);width: 30px;height: 30px;border-radius: 50%;background: inherit; background: #46A7A3 !important;border-color: #46A7A3 !important;\"> "
			+ "</span></td><td></td></tr>";
	private final static String rightRow = "<tr><td ></td>" + "<td style=\"width:20%;text-align:center\">"
			+ "</td><td style=\"border:1px solid #46A7A3;background:#46A7A3;color:#dddddd;float:left;width:90%\">"
			+ "<time style=\"font-size: 1.2rem;display: block;font-weight: bold;margin-bottom: 8px;\">{0}</time>"
			+ "{1} {2}  {3} {4} {5}</td></tr>";
	private final static String endTableEvento = "</table>";
	private final static String content_type = "text/html;charset=UTF-8";
	private final static String subject_email = "Relatório diário de eventos - {0}";
	
	public void sendMessage(EventoEmailModel model){
		String content = mountContent(model);
		String subject = MessageFormat.format(subject_email, model.getResidencia());
		String to_email = model.getResponsaveis().get(0).getEmail();
		for(int i=1;i< model.getResponsaveis().size();i++){
			to_email = to_email.concat(",").concat(model.getResponsaveis().get(i).getEmail());
		}
		sendEmail.send(to_email, subject, content, content_type);
		
	}
	
	public String mountContent(EventoEmailModel model){
		String _panelFoto = mountPanelFoto(model.getData(), model.getPaciente(), model.getIdpaciente());
		String _memorando = mountMemo(model.getMemorando());
		String _timeline = mountTimeline(model.getEventos());
		String content = header.concat(_panelFoto).concat(_memorando).concat(_timeline).concat(footer);
		return content;
	}

	private String mountMemo(String memorando) {
		if (memorando == null || memorando.trim().length() == 0) {
			memorando = Bundle.getValue("no_memorando");
		}
		String _panel = MessageFormat.format(panelAnotacao, memorando);
		_panel = _panel.concat(footerpanel);
		return _panel;
	}

	private String mountPanelFoto(Date data, String paciente, Integer idpaciente) {
		String date = DateUtil.convertDate(data);
		String _panelfoto = MessageFormat.format(headerpanelfoto, date, paciente);
		String _foto = MessageFormat.format(contentpanelfoto, idpaciente);
		_panelfoto = _panelfoto.concat(_foto).concat(footerpanel);
		return _panelfoto;
	}

	private String mountTimeline(List<Evento> eventos) {
		String _timeline = headerpanelEvento;

		if (eventos.isEmpty()) {
			_timeline = _timeline.concat(Bundle.getValue("no_register"));
			_timeline = _timeline.concat(footerpanel);
			return _timeline;
		}
		_timeline = _timeline.concat(tablepanelEvento);
		boolean left = true;
		for (int i = 0; i < eventos.size(); i++) {
			Evento evento = eventos.get(i);
			String row = null;
			if (left) {
				row = MessageFormat.format(leftRow, lineEvento(evento.getHour()), lineEvento(evento.getData()),
						lineEvento(evento.getGrupoevento()), lineEvento(evento.getSubgrupoevento()),
						lineEvento(evento.getRespeventos()), lineEvento(evento.getObsevento()),
						lineCuidador(evento.getUsuario().getApelido()));
				if (i < (eventos.size() - 1)) {
					row = row.concat(centerRow);
				}

			} else {
				row = MessageFormat.format(rightRow, lineEvento(evento.getHour()), lineEvento(evento.getData()),
						lineEvento(evento.getGrupoevento()), lineEvento(evento.getSubgrupoevento()),
						lineEvento(evento.getRespeventos()), lineEvento(evento.getObsevento()),
						lineCuidador(evento.getUsuario().getApelido()));
			}
			_timeline = _timeline.concat(row);
			left = !left;
		}
		_timeline = _timeline.concat(endTableEvento).concat(footerpanel);
		return _timeline;
	}

	private String lineEvento(String descricao) {
		if (descricao == null || descricao.trim().length() == 0) {
			return "";
		}
		return descricao.concat("<br/>");
	}

	private String lineCuidador(String descricao) {
		if (descricao == null || descricao.trim().length() == 0) {
			return "";
		}
		return "Cuidador: ".concat(descricao);
	}
}
