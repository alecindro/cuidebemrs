package br.cuidebem.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.def.Check;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.model.util.PhotoUtil;
import br.com.cuidebem.model.view.CheckReport;
import br.com.cuidebem.model.view.CheckReportFactory;

public class TestBasic {

	public static void main(String[] args) {
	
	}
	
	public static String convertDate(){
		Calendar calendar = Calendar.getInstance();
		
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd_MM_yyyy");
		return LocalDate.of(ano, mes, dia).format(formatador);

	}
	
	public static void testEvento(){
		List<Evento> eventos = new ArrayList<>();
		Evento evento1 = new Evento();
		Evento evento2 = new Evento();
		Evento evento3 = new Evento();
		Evento evento4 = new Evento();
		Evento evento5 = new Evento();
		Evento evento6 = new Evento();
		Date date = Calendar.getInstance().getTime();
		evento1.setDataregistro(DateUtil.sumMinutes(date, 0));
		evento1.setGrupoevento(Check.CHECKOUT.getDescricao());
		evento2.setDataregistro(DateUtil.sumMinutes(date, 120));
		evento2.setGrupoevento(Check.CHECKIN.getDescricao());
		evento3.setDataregistro(DateUtil.sumMinutes(date, 240));
		evento3.setGrupoevento(Check.CHECKOUT.getDescricao());
		evento4.setDataregistro(DateUtil.sumMinutes(date, 360));
		evento4.setGrupoevento(Check.CHECKIN.getDescricao());
		evento5.setDataregistro(DateUtil.sumMinutes(date, 480));
		evento5.setGrupoevento(Check.CHECKOUT.getDescricao());
		evento6.setDataregistro(DateUtil.sumMinutes(date, 600));
		evento6.setGrupoevento(Check.CHECKIN.getDescricao());
		
		eventos.add(evento1);
		eventos.add(evento2);
		eventos.add(evento3);
		eventos.add(evento4);
		eventos.add(evento5);
		eventos.add(evento6);
		
		TestBasic.mountcheckin(eventos);
		TestBasic.mountcheckout(eventos);
	}
	
	public static void mountcheckin(List<Evento> eventos){
		List<CheckReport> list =  CheckReportFactory.mountcheckin(eventos);
		System.out.println("===checkin====");
		for(CheckReport checkreport: list ){
			System.out.println(checkreport.toString());
		}
	}
	
public static void mountcheckout(List<Evento> eventos){
	List<CheckReport> list = CheckReportFactory.mountcheckout(eventos);
	System.out.println("===checkout====");
	for(CheckReport checkreport: list ){
		System.out.println(checkreport.toString());
	}
	}
public static void testImage64(){
	File file = new File("d:/download.png");
	try {
		byte[] bytes = Files.readAllBytes(Paths.get("d:/download.png"));
		bytes = PhotoUtil.base64(bytes);
		System.out.println(new String(bytes,"UTF-8"));
		//bytes = PhotoUtil.decoderBase64(bytes);
		//System.out.println(new String(bytes,"UTF-8"));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}	
	

}
