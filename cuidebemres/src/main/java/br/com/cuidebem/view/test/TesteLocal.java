package br.com.cuidebem.view.test;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class TesteLocal {

	public void test(){
		URL url = getClass().getResource("/"+"dor.png");
		System.out.println(url.toString());
	}
	
	public void testeNome(){
		String nome ="  Joao da     silva";
		String[] nomes = nome.trim().split("\\s");
		for(String _n : nomes){
			System.out.println(_n);
		}
	}
	
	public void testData(){
		LocalDate local = LocalDate.now();
		System.out.println( local.getMonth().getDisplayName(TextStyle.FULL,Locale.getDefault()));
	}
	
	public void testMonthBirth(){
		DateTimeFormatter formatador = 
				  DateTimeFormatter.ofPattern("dd-MM");
		String data  = "08-09";
		MonthDay monthDay = MonthDay.parse(data, formatador);
		System.out.println(monthDay.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
		System.out.println(monthDay.getDayOfMonth());
		
	}
	
	public void litLocales(){
		  Locale[] ll = Locale.getAvailableLocales();
		    for (int i = 0; i < ll.length; i++){
		      System.out.println(ll[i]);
		  }
	}
	
	public void testSqlData(){
		java.util.Date utilDate = new java.util.Date("2015/08/12 23:09:00");
	    java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
	    System.out.println("utilDate:" + utilDate);
	    System.out.println("sqlDate:" + sqlDate);
	}
	
	public void getDateNow(){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		System.out.println(LocalDateTime.now().format(formatador));
	}
	
	public void daysOfWeek(){
		for (DayOfWeek dayOfWeek : DayOfWeek.values()){
			String name = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
			System.out.println(name);
		}
	}
	
	public static void main(String[] args) {
		TesteLocal tt = new TesteLocal();
		tt.litLocales();
		

	}

}
