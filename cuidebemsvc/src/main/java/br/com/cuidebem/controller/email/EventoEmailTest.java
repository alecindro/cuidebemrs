package br.com.cuidebem.controller.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.util.DateUtil;

public class EventoEmailTest {

	
	public static void main(String[] args){
		EventoEmailModel model = EventoEmailTest.mount();
		try {
			EventoEmailTest.geraEmail(model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static EventoEmailModel mount(){
		
		Date data = new Date();
		EventoEmailModel eventoEmailModel = new EventoEmailModel();
		eventoEmailModel.setData(data);
		eventoEmailModel.setMemorando("<span style=\"font-weight: normal;\">teste </span><span style=\"font-weight: bold; color: rgb(255, 0, 0);\">testando</span>");
		//eventoEmailModel.setMemorando(null);
		eventoEmailModel.setPaciente("Isadora");
		eventoEmailModel.setIdpaciente(2);
		
		eventoEmailModel.setResidencia("Teste casa lar");
		for(int i=0;i<5;i++){
			
			Evento evento = new Evento();
			evento.setHour(DateUtil.convertHour(data));
			evento.setData(DateUtil.convertDate(data));
			evento.setGrupoevento("Nutrição" +i);
			evento.setSubgrupoevento("Almoço"+i);
			evento.setRespeventos("Almoçou bem");
			evento.setObsevento("teste obs"+i);
			evento.setUsuario(new Usuario());
			 evento.getUsuario().setApelido("cuidador"+i);
			 eventoEmailModel.getEventos().add(evento);
		}
		return eventoEmailModel;
	}
	
	
	public static void geraEmail(EventoEmailModel eventoEmailModel) throws IOException{
		EventoMail er = new EventoMail();
		String email = er.mountContent(eventoEmailModel);
		Files.write(Paths.get("d:/email.html"), email.getBytes());
	}
	
}
