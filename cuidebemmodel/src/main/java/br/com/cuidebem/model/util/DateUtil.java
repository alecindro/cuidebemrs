package br.com.cuidebem.model.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {

	public static String convertHour(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		return LocalTime.of(hour, minute).toString();
	}
	
	public static Date convertHour(Date date, String hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String[] values = hour.split(":");
		int _hour = Integer.valueOf(values[0]);
		int minute = Integer.valueOf(values[1]);
		calendar.set(Calendar.HOUR_OF_DAY, _hour);
		calendar.set(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	public static Date sumDateDays(Date date, Integer days){
		return Date.from(date.toInstant().plus(days, ChronoUnit.DAYS));
	}
	
	public static Date sumMinutes(Date date, Integer minutes){
		return Date.from(date.toInstant().plus(minutes, ChronoUnit.MINUTES));
	}
	
	public static Date getZeroHour(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		return calendar.getTime();
	}
	
	public static java.sql.Timestamp toSqlDAte(Date date){
		return new java.sql.Timestamp(date.getTime());
	}

	
	public static String getDateNow(){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return LocalDateTime.now().format(formatador);
	}

	public static Date convertDate(String dateinput) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String[] values = dateinput.split("/");
		calendar.set(Calendar.YEAR, Integer.valueOf(values[2]));
		calendar.set(Calendar.MONTH, Integer.valueOf(values[1]));
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(values[0]));
		return calendar.getTime();
	}

	public static String convertDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.of(ano, mes, dia).format(formatador);

	}

	public static MonthDay getMonthDay(String pattern, String data) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern(pattern);
		return MonthDay.parse(data, formatador);
	}
	
	public static List<String> daysOfWeek(){
		List<String> list = new ArrayList<>();
		for (DayOfWeek dayOfWeek : DayOfWeek.values()){
			String name = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
			list.add(name);
		}
		return list;
	}
	
	public static List<Date> listDates(Date inicio, Date fim, String hour){
		List<Date> dates = new ArrayList<Date>();
		Instant inicioInstant = convertHour(inicio, hour).toInstant();
		Instant fimInstant = convertHour(fim, hour).toInstant();
		
		while(inicioInstant.compareTo(fimInstant)<=0){
			Date date = Date.from(inicioInstant);
			dates.add(date);
			inicioInstant = inicioInstant.plus(1,ChronoUnit.DAYS);
		}
		return dates;
	}
	
	public static List<Date> listDates(Date inicio, Date fim, String hour,int repeatHour){
		List<Date> dates = new ArrayList<Date>();
		Instant inicioInstant = convertHour(inicio, hour).toInstant();
		Instant fimInstant = convertHour(fim, hour).toInstant();
		while(inicioInstant.compareTo(fimInstant)<=0){
			Date date = Date.from(inicioInstant);
			dates.add(date);
			inicioInstant = inicioInstant.plus(Duration.ofHours(repeatHour));
		}
		return dates;
	}
	public static List<Date> listDates(Date inicio, Date fim, String hour,Integer[] daysOfWeek){
		List<Integer> days = Arrays.asList(daysOfWeek);
		List<Date> dates = new ArrayList<Date>();
		
		Instant inicioInstant = convertHour(inicio, hour).toInstant();
		Instant fimInstant = convertHour(fim, hour).toInstant();
		while(inicioInstant.compareTo(fimInstant)<=0){
			if(days.contains(LocalDateTime.ofInstant(inicioInstant, ZoneId.of("America/Sao_Paulo")) .get(ChronoField.DAY_OF_WEEK))){
				Date date = Date.from(inicioInstant);
				dates.add(date);
			}
			inicioInstant = inicioInstant.plus(1,ChronoUnit.DAYS);
		}
		return dates;
	}
	
	public static String dayOfWeek(Date date){
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("America/Sao_Paulo")).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
	}
	
	public static Integer dayValueOfWeek(Date date){
		if(date == null){
			return -1;
		}
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("America/Sao_Paulo")).getDayOfWeek().getValue();
	}
	

}
