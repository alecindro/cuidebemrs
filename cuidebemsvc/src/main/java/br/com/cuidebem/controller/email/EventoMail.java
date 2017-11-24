package br.com.cuidebem.controller.email;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.cuidebem.ContentIdGenerator;
import br.com.cuidebem.FileMail;
import br.com.cuidebem.SendEmail;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.PacientePhoto;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.model.util.PhotoUtil;
import br.com.cuidebem.translate.Bundle;

@Stateless
public class EventoMail {

	@EJB
	private SendEmail sendEmail;

	private final static String header = "<html><head></head><body><div style=\"display: flex;flex-wrap: wrap;\"><div style=\"width:100%;\">";
	private final static String footer = "</div></div></body></html>";
	private final static String headerpanelfoto = "<div  style=\"padding: .2em;border-radius: 3px;border: 1px solid #262626;color: #dddddd;background-color: #4d4d4d;background: -webkit-radial-gradient(50% 75%,circle,#676767,#343434);\">"
			+ "<div  style=\"padding: .5em 1em .3em;text-align: center;font-weight: normal;border-radius: 3px;background: linear-gradient(#99a699,#778877);outline: 0;border: 1px solid #3c443c;color: #eeffee;\">"
			+ "<span style=\"margin: .1em 16px .2em 0;\">" + "Diário do dia {0} - {1}</span></div>";
	private final static String contentpanelfoto = "<div  style=\"border: 0; background: 0; padding: .5em 1em; color: #dddddd; text-align:center\"><img src=\"cid:{0}\" style=\"height: auto\"></img>";
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
			+ "{1} {2}  {3} {4} {5} {6}</td>" + "<td style=\"text-align:center;width:20%\">" + "</td><td></td></tr>";
	private final static String centerRow = "<tr style=\"height: 30px\"><td></td><td style=\"vertical-align: baseline;text-align:center;width:20%\">"
			+ "<span class=\"ui-btn-timeline\" style=\"content: '';position: absolute;transform: translateX(-50%);width: 30px;height: 30px;border-radius: 50%;background: inherit; background: #46A7A3 !important;border-color: #46A7A3 !important;\"> "
			+ "</span></td><td></td></tr>";
	private final static String rightRow = "<tr><td ></td>" + "<td style=\"width:20%;text-align:center\">"
			+ "</td><td style=\"border:1px solid #46A7A3;background:#46A7A3;color:#dddddd;float:left;width:90%\">"
			+ "<time style=\"font-size: 1.2rem;display: block;font-weight: bold;margin-bottom: 8px;\">{0}</time>"
			+ "{1} {2}  {3} {4} {5} {6}</td></tr>";
	private final static String endTableEvento = "</table>";
	private final static String headerFotos = "<div  style=\"padding: .2em;border-radius: 3px;border: 1px solid #262626;color: #dddddd;background-color: #4d4d4d;background: -webkit-radial-gradient(50% 75%,circle,#676767,#343434);\">"
			+"<div  style=\"padding: .5em 1em .3em;text-align: center;font-weight: normal;border-radius: 3px;background: linear-gradient(#99a699,#778877);outline: 0;border: 1px solid #3c443c;color: #eeffee;\">"
			+"<span style=\"margin: .1em 16px .2em 0;\">Fotos</span></div>";
	private final static String contentFotos = "<div style=\"border:0; background:0; padding:.5em 1em; color:#dddddd; width:240px; height:auto;display:inline-block\">"
+"<img style=\"height:auto; witdh:100%\" src=\"cid:{0}\" ></img>"
+"<p style=\"border:1px solid #46A7A3;background:#46A7A3;color:#dddddd;text-align:center; width: 100%\">{1}</p></div>";
	private final static String footerFotos = "</div>";
	private final static String content_type = "text/html;charset=UTF-8";
	private final static String subject_email = "Relatório diário de eventos - {0}";
	private static String urlpacientefoto = "http://lar.cuidebemapp.com/rs/images/paciente/{0}";
	private static String urlpacienteids = "http://lar.cuidebemapp.com/rs/pacientephotos/diaria/{0}/{1}";
	private static String urlfotodiaria = "http://lar.cuidebemapp.com/rs/images/pacientedia/{0}";
	
	
	private List<FileMail> fileMails;
	
	public void sendMessage(EventoEmailModel model){
		fileMails = new ArrayList<FileMail>();
		String content = mountContent(model);
		String subject = MessageFormat.format(subject_email, model.getResidencia());
		String to_email = model.getResponsaveis().get(0).getEmail();
		for(int i=1;i< model.getResponsaveis().size();i++){
			to_email = to_email.concat(",").concat(model.getResponsaveis().get(i).getEmail());
		}
		
		sendEmail.send(to_email, subject, content, content_type,fileMails);
	}
	
	private String generateUrl(String url,Object... parameters){
		return MessageFormat.format(url, parameters);
	}
	
	public String mountContent(EventoEmailModel model){
		
		String _panelFoto = mountPanelFoto(model.getData(), model.getPaciente(),model.getIdpaciente());
		String _memorando = mountMemo(model.getMemorando());
		String _timeline = mountTimeline(model.getEventos());
		String _fotosDiarias = mountFotos(model.getIdpaciente(), model.getData(), model.getPaciente());
		String content = header.concat(_panelFoto).concat(_memorando).concat(_fotosDiarias).concat(_timeline).concat(footer);
		return content;
	}
	
	private String mountFotos(Integer idpaciente,Date date,String paciente){
		String result = "";
		List<PacientePhoto> list = loadPacientePhoto(idpaciente, date);
		if(list!= null && !list.isEmpty()){
			result = result.concat(headerFotos);
			for(PacientePhoto pacientePhoto: list){
				Integer idfoto = pacientePhoto.getIdpacientephoto();
				String _url = generateUrl(urlfotodiaria, idfoto);
				String cid = ContentIdGenerator.getContentId();
				File photo = loadImage(paciente+"_"+pacientePhoto.getIdpacientephoto(),_url,pacientePhoto.getType());
				fileMails.add(new FileMail(cid, photo));
				String _foto = MessageFormat.format(contentFotos, cid,pacientePhoto.getDescricao());
				result = result.concat(_foto);
			}
			result = result.concat(footerFotos);
		}
		return result;
	}
	
	

	private String mountMemo(String memorando) {
		if (memorando == null || memorando.trim().length() == 0) {
			//memorando = Bundle.getValue("no_memorando");
			return "";
		}
		String _panel = MessageFormat.format(panelAnotacao, memorando);
		_panel = _panel.concat(footerpanel);
		return _panel;
	}

	private String mountPanelFoto(Date data, String paciente, Integer idpaciente) {
		String _url = generateUrl(urlpacientefoto, idpaciente);
		String cid = ContentIdGenerator.getContentId();
		File photo = loadImage(paciente,_url,null);
		fileMails.add(new FileMail(cid, photo));
		String date = DateUtil.convertDate(data);
		String _panelfoto = MessageFormat.format(headerpanelfoto, date, paciente);
		String _foto = MessageFormat.format(contentpanelfoto, cid);
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
	
	private File loadImage(String descricao, String url, String type ){
		InputStream stream = null;
		File file  = null;
		try {
			
			URL _url = new URL(url);
			URLConnection conn = _url.openConnection();
			stream = conn.getInputStream();
			if(type == null){
				type = "image/jpg";
			}
			String _type = PhotoUtil.getExtension(type);
			final BufferedImage bufferedImage = ImageIO.read(stream);
			 file  = new File(descricao.concat(".").concat(_type));
			 
			  ImageIO.write(bufferedImage, _type, file);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(stream != null){
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	private List<PacientePhoto> loadPacientePhoto(Integer id,Date date){
		List<PacientePhoto> list = new ArrayList<PacientePhoto>();
		Client client = javax.ws.rs.client.ClientBuilder.newClient();
		String _url = java.text.MessageFormat.format(urlpacienteids, id,DateUtil.convertDateUnderscore(date));
		WebTarget webTarget = client.target(_url);
		List<Object> objects = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(List.class);
		ObjectMapper mapper = new ObjectMapper();
		for(Object _object : objects){
			Map map = (Map) _object; 
			PacientePhoto pacientePhoto = new PacientePhoto();
			pacientePhoto.setIdpacientephoto((Integer) map.get("idpacientephoto"));
			pacientePhoto.setDescricao((String) map.get("descricao"));
			pacientePhoto.setType((String) map.get("type"));
			list.add(pacientePhoto);
		}
		return list;
	}
}
