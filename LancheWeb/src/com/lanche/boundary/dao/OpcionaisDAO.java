package com.lanche.boundary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lanche.entity.Opcionais;
import com.lanche.utils.ArquivosConfig;

public class OpcionaisDAO extends DAO<Opcionais> {

	@Override
	public Opcionais searchByID(int id) {
		if (openConnection()) {
			Opcionais opcional = null;
			try {
				
				comando = con.prepareStatement(ArquivosConfig.opcionaisSearchByID);
				comando.setInt(1, id);

				ResultSet r = comando.executeQuery();
				if (r.next())
					opcional = new Opcionais(r.getInt(1), r.getString(2),
							r.getDouble(3), r.getBoolean(4), r.getDate(5),
							r.getDate(6));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return opcional;

		}

		return null;
	}

	public ArrayList<Opcionais> searchByLanche(int idLanche){
		if (openConnection()) {
			ArrayList<Opcionais> list = new ArrayList<Opcionais>();
			try {

				comando = con.prepareStatement(ArquivosConfig.opcionaisSearchByLanche);
				comando.setInt(1, idLanche);

				ResultSet r = comando.executeQuery();
				while (r.next()) {
					Opcionais opcional = new Opcionais(r.getInt(1), r.getString(2),
							r.getDouble(3), r.getBoolean(4), r.getDate(5),
							r.getDate(6));

					list.add(opcional);
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
	public boolean delete(Opcionais t) {
		boolean ret = false;
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.opcionaisDelete);
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
	public boolean persist(Opcionais t) {
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

	private void insert(Opcionais t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.opcionaisInsert,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, t.getDescricao());
			comando.setDouble(2, t.getPrecoAdicional());
			comando.setBoolean(3, t.permiteMaisQueUm());
			comando.setDate(4, (Date) t.getDtCadastro());
			comando.setDate(5, (Date) t.getDtModificacao());
			
			if(comando.executeUpdate()>0){
				ResultSet r = comando.getResultSet();
				if(r.next())
					t.setId(r.getInt(1));
			}	
		}
	}

	private void update(Opcionais t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.opcionaisUpdate);
			comando.setString(1, t.getDescricao());
			comando.setDouble(2, t.getPrecoAdicional());
			comando.setBoolean(3, t.permiteMaisQueUm());
			comando.setDate(4, (Date) t.getDtCadastro());
			comando.setDate(5, (Date) t.getDtModificacao());
			comando.setInt(6, t.getId());
			
			comando.executeUpdate();	
		}
		
	}

}
