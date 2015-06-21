package com.lanche.boundary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lanche.entity.ItemPedido;
import com.lanche.entity.Lanche;
import com.lanche.utils.ArquivosConfig;

public class ItemPedidoDAO extends DAO<ItemPedidoDAO> {

	@Override
	public ItemPedidoDAO searchByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ItemPedido> getAllFromPedido(int idPedido) {
		if (openConnection()) {
			ArrayList<ItemPedido> list = new ArrayList<ItemPedido>();
			try {

				comando = con.prepareStatement(arq
						.getProperty(ArquivosConfig.itemPedidoSearchByPedido));
				comando.setInt(1, idPedido);

				ResultSet r = comando.executeQuery();
				while (r.next()) {
					ItemPedido itemPed = new ItemPedido(r.getInt(1), r.getInt(2), r
							.getInt(3), new Lanche(r.getInt(4), r.getString(5),
							r.getDouble(6), r.getDate(7), r.getDate(8), r.getBoolean(9)));
					OpcionaisDoItemDAO dao = new OpcionaisDoItemDAO();
					itemPed.setOpcionais(dao.getAllFromItemPedido(itemPed.getId()));
					list.add(itemPed);
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

	@Override
	public boolean delete(ItemPedidoDAO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean persist(ItemPedidoDAO t) {
		// TODO Auto-generated method stub
		return false;
	}

}
