package br.com.cuidebem.algoritmo;

public interface Algoritmo {

	public default Integer getMaxValue(){
		return 100;
	}
	
	public default Integer getMinValue(){
		return 0;
	}
	
	Integer compute(String value);
	
	
	
}
