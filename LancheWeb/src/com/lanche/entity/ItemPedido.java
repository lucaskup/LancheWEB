package com.lanche.entity;

import java.util.ArrayList;

public class ItemPedido {
	
	private int id;
	private int numItem;
	private int quantidade;
	private Lanche lanche;
	private ArrayList<OpcionaisDoItem> opcionais;
	
	
	public ItemPedido(int id, int numItem, int quantidade, Lanche lanche) {
		super();
		this.id = id;
		this.numItem = numItem;
		this.quantidade = quantidade;
		this.lanche = lanche;

	}


	/**
	 * 
	 * @return Retorna o total do item já incluindo o valor dos opcionais
	 */
	public double getTotal(){
		double preco = 0;
		preco = lanche.getPreco();
		if(opcionais != null){
			for (OpcionaisDoItem opcional : opcionais) {
				preco += opcional.getOpcional().getPrecoAdicional()*opcional.getQuantidade();
			}
		}
		return preco;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumItem() {
		return numItem;
	}
	public void setNumItem(int numItem) {
		this.numItem = numItem;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Lanche getLanche() {
		return lanche;
	}
	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}
	public ArrayList<OpcionaisDoItem> getOpcionais() {
		return opcionais;
	}
	public void setOpcionais(ArrayList<OpcionaisDoItem> opcionais) {
		this.opcionais = opcionais;
	}


}
