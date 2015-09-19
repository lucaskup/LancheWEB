package com.lanche.entity;

public class Configuracoes {
	private int ultimoIDPedido;
	private int ultimoNumeroPedido;
	
	public Configuracoes(int ultimoIDPedido, int ultimoNumeroPedido) {
		super();
		this.ultimoIDPedido = ultimoIDPedido;
		this.ultimoNumeroPedido = ultimoNumeroPedido;
	}
	public int getUltimoIDPedido() {
		return ultimoIDPedido;
	}
	public void setUltimoIDPedido(int ultimoIDPedido) {
		this.ultimoIDPedido = ultimoIDPedido;
	}
	public int getUltimoNumeroPedido() {
		return ultimoNumeroPedido;
	}
	public void setUltimoNumeroPedido(int ultimoNumeroPedido) {
		this.ultimoNumeroPedido = ultimoNumeroPedido;
	}
	
	

}
