package com.lanche.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.lanche.boundary.dao.LancheDAO;
import com.lanche.boundary.dao.OpcionaisDAO;
import com.lanche.boundary.dao.PedidoDAO;
import com.lanche.entity.ItemPedido;
import com.lanche.entity.Lanche;
import com.lanche.entity.OpcionaisDoItem;
import com.lanche.entity.Pedido;
import com.lanche.entity.enums.Status;

public class PedidoController {
	
	public List<Pedido> getAll(boolean exibirApenasHoje, boolean exibirCadastrado, boolean exibirFazendo, boolean exibirPronto){
		PedidoDAO dao = new PedidoDAO();
		List<Pedido> lista = new ArrayList<Pedido>();
		if(exibirApenasHoje){
			lista = dao.getAllFromToday();
		}else{
			lista = dao.getAll();
		}
		ArrayList<Pedido> ret = new ArrayList<Pedido>();
		for (Pedido pedido : lista) {
			switch (pedido.getStatus()) {
			case CADASTRADO:
				if(exibirCadastrado)
					ret.add(pedido);
				break;
			case FAZENDO:
				if(exibirFazendo)
					ret.add(pedido);
				break;
			case PRONTO:
				if(exibirPronto)
					ret.add(pedido);
				break;
			default:
				break;
			}
		}
		return ret;
	}
	
	public List<Pedido> getAll(){
		PedidoDAO dao = new PedidoDAO();
		return dao.getAll();
	}
	public List<Pedido> getAllToday(){
		PedidoDAO dao = new PedidoDAO();
		return dao.getAllFromToday();
	}
	public boolean createOrUpdateLanche(Pedido p){
		PedidoDAO dao = new PedidoDAO();
		
		try {
			return dao.persist(p);
		} catch (Exception e) {
			return false;
		}
		
	}
	public boolean delete(int id) {
		PedidoDAO dao = new PedidoDAO();
		return dao.delete(id);
		
	}
	public boolean passarParaPronto(int idPedido) {
		PedidoDAO dao = new PedidoDAO();
		return dao.updateStatus(idPedido,Status.PRONTO);
	}
	public boolean passarParaFazendo(int idPedido) {
		PedidoDAO dao = new PedidoDAO();
		return dao.updateStatus(idPedido,Status.FAZENDO);
	}
	public void criarPedidoJson(String json) {
		ArrayList<ItemPedido> lista = new ArrayList<ItemPedido>();
		LancheDAO dao = new LancheDAO();
		OpcionaisDAO daoOp = new OpcionaisDAO();
		PedidoDAO daoPedido = new PedidoDAO();
		JSONArray array = new JSONArray(json);
		String opcionaisCSV = "";
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			Lanche lanche = dao.searchByID(item.getInt("id"));
			ItemPedido iPedido = new ItemPedido(0,i,item.getInt("quantidade"),lanche); 
			
			opcionaisCSV = item.getString("opcionais");
			if(!opcionaisCSV.isEmpty()){
				String[] opcionais = opcionaisCSV.split(",");
				for (int j = 0; j < opcionais.length; j++) {
					try {
						iPedido.getOpcionais().add(new OpcionaisDoItem(daoOp.searchByID(Integer.parseInt(opcionais[j])),1));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			lista.add(iPedido);
		}
		Pedido p = new Pedido(lista);
		daoPedido.persist(p);
	}

}
