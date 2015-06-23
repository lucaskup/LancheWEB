package com.lanche.boundary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lanche.entity.Pedido;
import com.lanche.entity.Usuario;
import com.lanche.entity.enums.Funcao;
import com.lanche.entity.enums.Status;
import com.lanche.utils.ArquivosConfig;

public class PedidoDAO extends DAO<Pedido> {

	@Override
	public Pedido searchByID(int id) {
		if (openConnection()) {
			Pedido pedido = null;
			try {

				comando = con.prepareStatement(arq
						.getProperty(ArquivosConfig.pedidoSearchByID));
				comando.setInt(1, id);
				ResultSet r = comando.executeQuery();
				pedido = new Pedido(r.getInt(1), r.getDate(2), r.getDate(3),
						Status.getStatus((r.getInt(4))), new Usuario(
								r.getInt(5),r.getString(6), r.getString(7), r.getString(8),
								r.getDate(9), r.getString(10),
								Funcao.getFuncao(r.getInt(11)), r.getString(12), r.getBoolean(13)));
				ItemPedidoDAO dao = new ItemPedidoDAO();
				pedido.setItens(dao.getAllFromPedido(id));
				//pedido.setItens(itens);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return pedido;

		}

		return null;
	}

	@Override
	public boolean delete(Pedido t) {
		boolean ret = false;
		if(openConnection()){
			try {
				comando = con.prepareStatement(arq.getProperty(ArquivosConfig.pedidoDelete));
				comando.setInt(1, t.getId());
				ret = comando.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;	
	}

	@Override
	public boolean persist(Pedido t) {
		// TODO Auto-generated method stub
		return false;
	}

}
