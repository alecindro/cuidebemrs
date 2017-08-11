package br.com.cuidebem.controller.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {

	public static String convertHour(Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		return LocalTime.of(hour, minute).toString();
	}

	public static Date convertDate(String dateinput) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String[] values = dateinput.split("/");
		calendar.set(Calendar.YEAR, Integer.valueOf(values[2]));
		calendar.set(Calendar.MONTH, Integer.valueOf(values[1]));
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(values[0]));
		return calendar.getTime();
	}

	public static String convertDate(Date date) throws Exception {
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

}
