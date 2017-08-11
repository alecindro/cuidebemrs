package br.com.cuidebem.controller;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {

	private Map<String, Object> parameters = null;

	private QueryParameter(String name, Object value) {
		this.parameters = new HashMap<String, Object>();
		this.parameters.put(name, value);
	}

	public static QueryParameter init(String name, Object value) {
		return new QueryParameter(name, value);
	}

	public QueryParameter add(String name, Object value) {
		this.parameters.put(name, value);
		return this;
	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	public static QueryParameter parameterEnabled() {
		return new QueryParameter("enabled", 1);
	}

}
