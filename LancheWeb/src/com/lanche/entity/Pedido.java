package com.lanche.entity;

import java.util.ArrayList;
import java.util.Date;

import com.lanche.entity.enums.Status;

public class Pedido {

	private int id;
	private Date dtCriacao;
	private Date dtModificacao;
	private Status status;
	private Usuario usuario;
	private ArrayList<ItemPedido> itens;
	private int numPedido;
	
	public Pedido() {
	}
	public Pedido(int id) {
		this(id, null, Status.CADASTRADO, null);
	}
	public Pedido(ArrayList<ItemPedido> itens){
		this(0, null, Status.CADASTRADO, null);
		this.itens = itens;
	}
	public Pedido(int id, Date dtCriacao, Status status,Usuario usurio) {
		this(id,dtCriacao,null,status,usurio);
	}
	public Pedido(int id, Date dtCriacao, Date dtModificacao, Status status,Usuario usurio) {
		this.id = id;
		this.dtCriacao = dtCriacao;
		this.status = status;
		this.usuario = usurio;
		this.dtModificacao = dtModificacao;
	}
	public Pedido(int id, Date dtCriacao, Date dtModificacao, Status status,Usuario usurio, int numPedido) {
		this(id, dtCriacao, dtModificacao, status, usurio);	
		this.numPedido = numPedido;
	}

/**
 * 
 * @return Retorna o valor de todos os itens e seus opcionais somados
 */
	public double getTotal(){
		double total = 0;
		if(itens != null){
			for (ItemPedido itemPedido : itens) {
				total += itemPedido.getTotal();
			}
		}
		return total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemPedido> itens) {
		this.itens = itens;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDtModificacao() {
		return dtModificacao;
	}

	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}
	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	public String getNomeUsuarioCriacao(){
		if(getUsuario() == null)
			return "Balcão";
		return getUsuario().getNome().trim()+" "+getUsuario().getSobrenome().trim();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Pedido)){
			return super.equals(obj);
		}
		
		return this.getId() == ((Pedido)obj).getId();
		
		
	}


}
