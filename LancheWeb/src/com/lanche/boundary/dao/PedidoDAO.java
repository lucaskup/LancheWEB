package com.lanche.boundary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.lanche.entity.Pedido;
import com.lanche.entity.Usuario;
import com.lanche.entity.enums.Status;
import com.lanche.utils.ArquivosConfig;

public class PedidoDAO extends DAO<Pedido> {

	@Override
	public Pedido searchByID(int id) {
		if (openConnection()) {
			Pedido pedido = null;
			try {

				comando = con.prepareStatement(ArquivosConfig.pedidoSearchByID);
				comando.setInt(1, id);
				ResultSet r = comando.executeQuery();
				pedido = getPedidoFromResultSet(r);
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

	private Pedido getPedidoFromResultSet(ResultSet r) throws SQLException {
		Date dtCadastro = null;
		Date dtModificacao = null;
		Timestamp tsCadastro = r.getTimestamp(2);
		Timestamp tsModificacao = r.getTimestamp(3);
		if(tsCadastro != null)
			dtCadastro = new Date(tsCadastro.getTime());
		if(tsModificacao != null)
			dtModificacao = new Date(tsModificacao.getTime());
		Pedido pedido;
		Usuario usu = null;
		if(r.getInt(5) != 0){
			UsuarioDAO dao = new UsuarioDAO();
			usu = dao.searchByID(r.getInt(5));
		}
		
		pedido = new Pedido(r.getInt(1), dtCadastro, dtModificacao,
				Status.getStatus((r.getInt(4))), usu,r.getInt(6));
		return pedido;
	}

	@Override
	public boolean delete(Pedido t) {
		boolean ret = false;
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.pedidoDelete);
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
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.pedidoInsert,Statement.RETURN_GENERATED_KEYS);

				if(t.getUsuario() != null){
					comando.setInt(1,t.getUsuario().getId());
				}else{
					comando.setNull(1, java.sql.Types.INTEGER);
				}
				
				comando.setInt(2, t.getStatus().getValor());
				
				if(comando.executeUpdate()>0){
					ResultSet r = comando.getGeneratedKeys();
					if(r.next())
						t.setId(r.getInt(1));
				}
				
				ItemPedidoDAO dao = new ItemPedidoDAO();
				dao.persist(t);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	
		return false;
	}

	public List<Pedido> getAll() {
		if (openConnection()) {
			ArrayList<Pedido> list = new ArrayList<Pedido>();
			try {
				comando = con.prepareStatement(ArquivosConfig.pedidoSearchAll);
				ResultSet r = comando.executeQuery();
				while (r.next()) {
					Pedido p = getPedidoFromResultSet(r);
					if(p != null){
						ItemPedidoDAO dao = new ItemPedidoDAO();
						p.setItens(dao.getAllFromPedido(p.getId()));
						list.add(p);
					}
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
	
	public List<Pedido> getAllFromToday() {
		if (openConnection()) {
			ArrayList<Pedido> list = new ArrayList<Pedido>();
			try {
				comando = con.prepareStatement(ArquivosConfig.pedidoSearchAllFromToday);
				Calendar c = new GregorianCalendar();
				c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				Date dtFiltroIni = new Date(c.getTimeInMillis());
				
				c.set(Calendar.HOUR_OF_DAY, 23); //anything 0 - 23
				c.set(Calendar.MINUTE, 59);
				c.set(Calendar.SECOND, 59);
							
				Date dtFiltroFin = new Date(c.getTimeInMillis());
				
				comando.setTimestamp(1, new java.sql.Timestamp(dtFiltroIni.getTime()));
				comando.setTimestamp(2, new java.sql.Timestamp(dtFiltroFin.getTime()));
				
				ResultSet r = comando.executeQuery();
				while (r.next()) {
					Pedido p = getPedidoFromResultSet(r);
					if(p != null){
						ItemPedidoDAO dao = new ItemPedidoDAO();
						p.setItens(dao.getAllFromPedido(p.getId()));
						list.add(p);
					}
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

	
	public boolean delete(int id) {
		Pedido p = new Pedido(id);
		return delete(p);
		
	}

	public boolean updateStatus(int idPedido, Status status) {
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.pedidoUpdateStatus);
				comando.setInt(1, status.getValor());
				comando.setInt(2, idPedido);
				
				comando.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		return false;
	}

}
