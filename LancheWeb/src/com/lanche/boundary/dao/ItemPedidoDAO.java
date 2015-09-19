package com.lanche.boundary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lanche.entity.ItemPedido;
import com.lanche.entity.Lanche;
import com.lanche.entity.Pedido;
import com.lanche.utils.ArquivosConfig;

public class ItemPedidoDAO extends DAO{

	public ArrayList<ItemPedido> getAllFromPedido(int idPedido) {
		if (openConnection()) {
			ArrayList<ItemPedido> list = new ArrayList<ItemPedido>();
			try {

				comando = con.prepareStatement(ArquivosConfig.itemPedidoSearchByPedido);
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

	public void persist(Pedido t) {
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.itemPedidoInsert,Statement.RETURN_GENERATED_KEYS);
				//numItem, fkLanche, fkPedido, quantidade
				for (ItemPedido item : t.getItens()) {

					comando.setInt(1, item.getNumItem());
					comando.setInt(2, item.getLanche().getId());
					comando.setInt(3, t.getId());
					comando.setInt(4, item.getQuantidade());
					
					
					if(comando.executeUpdate()>0){
						ResultSet r = comando.getGeneratedKeys();
						if(r.next())
							item.setId(r.getInt(1));
					}
					
					if(item.getOpcionais().size()>0){
						OpcionaisDoItemDAO dao = new OpcionaisDoItemDAO();
						dao.persist(item);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}

		
	}



}
