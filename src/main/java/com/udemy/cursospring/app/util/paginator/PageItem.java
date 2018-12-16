package com.udemy.cursospring.app.util.paginator;

public class PageItem {
	
	int numero;
	boolean actual;
	
	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}
	
	
	
}
