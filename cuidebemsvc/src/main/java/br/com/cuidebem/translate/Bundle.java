package br.com.cuidebem.translate;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {

	private static final String basename = "Bundle";

	public static String getValue(String key, Object... params) {
		String message  = ResourceBundle.getBundle(basename, Locale.getDefault()).getString(key);
		if (message == null) {
			return key;
		}
		return MessageFormat.format(message,
				params);
	}

	public static String getValue(Locale locale, String key) {
		String message = ResourceBundle.getBundle(basename, locale).getString(key);
		if (message == null) {
			return key;
		}
		return message;
	}

}
