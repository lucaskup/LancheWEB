package com.lanche.boundary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lanche.entity.ItemPedido;
import com.lanche.entity.Opcionais;
import com.lanche.entity.OpcionaisDoItem;
import com.lanche.utils.ArquivosConfig;

public class OpcionaisDoItemDAO extends DAO {

	public ArrayList<OpcionaisDoItem> getAllFromItemPedido(int idItemPedido) {
		if (openConnection()) {
			ArrayList<OpcionaisDoItem> list = new ArrayList<OpcionaisDoItem>();
			try {

				comando = con.prepareStatement(ArquivosConfig.opcionaisItemPedidoSearchByItem);
				comando.setInt(1, idItemPedido);

				ResultSet r = comando.executeQuery();
				while (r.next()) {
					list.add(new OpcionaisDoItem(new Opcionais(r.getInt(1), r
							.getString(2), r.getDouble(3), r.getBoolean(4), r
							.getDate(5), r.getDate(6)), r.getInt(7)));

					// Colocar os opcionais do item
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;

		}
		return null;
	}

	public void persist(ItemPedido item) {
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.opcionaisItemPedidoInsert);
				//fkItemPedido, fkOpcionais, quantidade
				for (OpcionaisDoItem opcional : item.getOpcionais()) {

					comando.setInt(1, item.getId());
					comando.setInt(2, opcional.getOpcional().getId());
					comando.setInt(3, opcional.getQuantidade());

					comando.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
	}

}
