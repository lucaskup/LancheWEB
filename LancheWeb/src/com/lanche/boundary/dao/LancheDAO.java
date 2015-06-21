package com.lanche.boundary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lanche.entity.Lanche;
import com.lanche.utils.ArquivosConfig;

public class LancheDAO extends DAO<Lanche> {

	@Override
	public Lanche searchByID(int id) {
		if (openConnection()) {
			Lanche lanche = null;
			try {
				
				comando = con.prepareStatement(arq
						.getProperty(ArquivosConfig.lancheSearchByID));
				comando.setInt(1, id);

				ResultSet r = comando.executeQuery();
				if (r.next())
					lanche = new Lanche(r.getInt(1), r.getString(2),
							r.getDouble(3), r.getDate(4),
							r.getDate(5), r.getBoolean(6));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return lanche;

		}

		return null;
	}

	public ArrayList<Lanche> getAll(){
		if (openConnection()) {
			ArrayList<Lanche> list = new ArrayList<Lanche>();
			try {
				comando = con.prepareStatement(arq
						.getProperty(ArquivosConfig.lancheSearchAll));
				ResultSet r = comando.executeQuery();
				while (r.next()) {
					Lanche lanche = new Lanche(r.getInt(1), r.getString(2),
							r.getDouble(3), r.getDate(4),
							r.getDate(5),r.getBoolean(6));

					list.add(lanche);
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
	public boolean delete(Lanche t) {
		boolean ret = false;
		if(openConnection()){
			try {
				comando = con.prepareStatement(arq.getProperty(ArquivosConfig.lancheDelete));
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
	public boolean persist(Lanche t) {
		if(openConnection()){
			boolean inseriu = true;
			try {
				if (t.getId() != 0 && searchByID(t.getId()) != null) {		
					update(t);		
				} else {
					insert(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				inseriu = false;
				
			} finally	{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return inseriu;
		}
		return false;
	}

	private void insert(Lanche t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(arq.getProperty(ArquivosConfig.lancheInsert),Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, t.getDescricao());
			comando.setDouble(2, t.getPreco());
			comando.setDate(3, (Date) t.getDtCadastro());
			comando.setDate(4, (Date) t.getDtModificacao());
			comando.setBoolean(5, t.isAtivo());
			
			if(comando.executeUpdate()>0){
				ResultSet r = comando.getResultSet();
				if(r.next())
					t.setId(r.getInt(1));
			}	
		}
	}

	private void update(Lanche t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(arq.getProperty(ArquivosConfig.lancheUpdate));
			comando.setString(1, t.getDescricao());
			comando.setDouble(2, t.getPreco());
			comando.setDate(3, (Date) t.getDtCadastro());
			comando.setDate(4, (Date) t.getDtModificacao());
			comando.setBoolean(5, t.isAtivo());
			comando.setInt(6, t.getId());
			
			comando.executeUpdate();	
		}
		
	}

}
