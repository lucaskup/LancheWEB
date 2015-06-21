package com.lanche.entity;

public class OpcionaisDoItem {
	private Opcionais opcional;
	private int quantidade;

	public OpcionaisDoItem(Opcionais opcional, int quantidade) {
		super();
		this.opcional = opcional;
		if(opcional.permiteMaisQueUm())
			this.quantidade = quantidade;
		else 
			this.quantidade = 1;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		if(getOpcional().permiteMaisQueUm())
			this.quantidade = quantidade;
		else
			this.quantidade = 1;
	}
	public Opcionais getOpcional() {
		return opcional;
	}
	public void setOpcional(Opcionais opcional) {
		this.opcional = opcional;
	}

}
