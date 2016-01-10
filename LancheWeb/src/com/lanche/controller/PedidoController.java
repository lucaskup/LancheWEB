package com.lanche.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.lanche.boundary.dao.LancheDAO;
import com.lanche.boundary.dao.OpcionaisDAO;
import com.lanche.boundary.dao.PedidoDAO;
import com.lanche.boundary.dao.TokenDAO;
import com.lanche.boundary.dao.UsuarioDAO;
import com.lanche.entity.ItemPedido;
import com.lanche.entity.Lanche;
import com.lanche.entity.OpcionaisDoItem;
import com.lanche.entity.Pedido;
import com.lanche.entity.Token;
import com.lanche.entity.Usuario;
import com.lanche.entity.enums.Status;

public class PedidoController {
	
	public List<Pedido> getAll(boolean exibirApenasHoje, boolean exibirCadastrado, boolean exibirFazendo, boolean exibirPronto, boolean exibirCancelado){
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
			case CANCELADO:
				if(exibirCancelado)
					ret.add(pedido);
				break;
			default:
				break;
			}
		}
		return ret;
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
	public boolean passarParaCancelado(int idPedido) {
		PedidoDAO dao = new PedidoDAO();
		return dao.updateStatus(idPedido,Status.CANCELADO);
	}
	public int criarPedidoJson(String json) {
		return criarPedidoJson(null, json);

	}

	public int criarPedidoJson(String login, String token, String pedidoJson) {
		UsuarioDAO du = new UsuarioDAO();
		Usuario u = du.searchByLogin(login);
		if(u == null)
			return 0;
		TokenDAO dToken = new TokenDAO();
			
		Token t = dToken.searchToken(u.getId(),token);
		if(t == null)
			return 0;
		Date d = new Date();
		if(Token.TEMPO_RENOVACAO_AUTOMATICA >= t.getValidade().getTime() - d.getTime()){
			t.setValidade(new Date(d.getTime()+Token.TEMPO_RENOVACAO_AUTOMATICA));
			return criarPedidoJson(u, pedidoJson);
		}	
		
		
		
		return 0;
	}

	private int criarPedidoJson(Usuario u, String json) {
		ArrayList<ItemPedido> lista = new ArrayList<ItemPedido>();
		LancheDAO dao = new LancheDAO();
		OpcionaisDAO daoOp = new OpcionaisDAO();
		PedidoDAO daoPedido = new PedidoDAO();
		JSONObject pedidoJson = new JSONObject(json);
		String obs = pedidoJson.getString("observacao");
		
		JSONArray array = pedidoJson.getJSONArray("itens");
		String opcionaisCSV = "";
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			Lanche lanche = dao.searchByID(item.getInt("id"));
			ItemPedido iPedido = new ItemPedido(0,i,item.getInt("quantidade"),lanche); 
			
			opcionaisCSV = item.getString("opcionais");
			if(!opcionaisCSV.trim().isEmpty()){
				String[] opcionais = opcionaisCSV.split(",");
				for (int j = 0; j < opcionais.length; j++) {
					try {
						iPedido.getOpcionais().add(new OpcionaisDoItem(daoOp.searchByID(Integer.parseInt(opcionais[j])),1));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			lista.add(iPedido);
		}
		Pedido p = new Pedido(obs,lista);
		p.setUsuario(u);
		daoPedido.persist(p);	
		p = daoPedido.searchByID(p.getId());
		return p.getNumPedido();
	}



}
