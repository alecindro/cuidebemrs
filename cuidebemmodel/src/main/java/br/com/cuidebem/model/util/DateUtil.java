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
import java.util.concurrent.TimeUnit;

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
	
	public static Date minHour(Date date, Integer hour){
		return Date.from(date.toInstant().minus(hour, ChronoUnit.HOURS));
	}
	
	public static Date getZeroHour(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	public static java.sql.Timestamp toSqlDAte(Date date){
		return new java.sql.Timestamp(date.getTime());
	}

	
	public static String getDateNow(){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return LocalDateTime.now().format(formatador);
	}
	
	public static Date convertDateUnderscore(String dateinput) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String[] values = dateinput.split("_");
		calendar.set(Calendar.YEAR, Integer.valueOf(values[2]));
		calendar.set(Calendar.MONTH, Integer.valueOf(values[1])-1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(values[0]));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static String convertDateUnderscore(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd_MM_yyyy");
		return LocalDate.of(ano, mes, dia).format(formatador);

	}

	public static Date convertDate(String dateinput) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String[] values = dateinput.split("/");
		calendar.set(Calendar.YEAR, Integer.valueOf(values[2]));
		calendar.set(Calendar.MONTH, Integer.valueOf(values[1])-1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(values[0]));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
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
	
	public static long getDifMinutes(Date initial,Date end){
		LocalDateTime _initial = LocalDateTime.ofInstant(initial.toInstant(), ZoneId.of("America/Sao_Paulo"));
		LocalDateTime _end = LocalDateTime.ofInstant(end.toInstant(), ZoneId.of("America/Sao_Paulo"));
		
		return ChronoUnit.MINUTES.between(_initial, _end);
	}
	
	public static String getDifHourMinute(Date initial,Date end){
		Duration dur = Duration.between(initial.toInstant(), end.toInstant());
		 long millis = dur.toMillis();
		return String.format("%02d:%02d", 
	             TimeUnit.MILLISECONDS.toHours(millis),
	             TimeUnit.MILLISECONDS.toMinutes(millis) - 
	             TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
	             );
	}
	
	public static int toMinutes(String hour){
		 String[] t = hour.split(":");
		    return Integer.valueOf(t[0]) * 60 + Integer.valueOf(t[1]);
	}
	
	public static String totalHours(int total) {
	    int minutes = total % 60;
	    int hours = ((total - minutes) / 60) % 24;
	    return String.format("%02d:%02d", hours, minutes);
	}
	
	public static boolean compareData(Date init, Date end){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(init);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(init);
		return calendar.equals(calendar2);
	}

}
