package com.lanche.boundary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.lanche.entity.Lanche;
import com.lanche.entity.Opcionais;
import com.lanche.utils.ArquivosConfig;

public class LancheDAO extends DAO<Lanche> {

	@Override
	public Lanche searchByID(int id) {
		if (openConnection()) {
			Lanche lanche = null;
			try {				
				comando = con.prepareStatement(ArquivosConfig.lancheSearchByID);
				comando.setInt(1, id);
				ResultSet r = comando.executeQuery();
				
				if (r.next()){
					lanche = getLancheFromResultSet(r);
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
			return lanche;

		}

		return null;
	}

	public ArrayList<Lanche> getAll(){
		if (openConnection()) {
			ArrayList<Lanche> list = new ArrayList<Lanche>();
			try {
				comando = con.prepareStatement(ArquivosConfig.lancheSearchAll);
				ResultSet r = comando.executeQuery();
				while (r.next()) {
					Lanche lanche = getLancheFromResultSet(r);
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
	
	public ArrayList<Lanche> getAtivos(){
		if (openConnection()) {
			ArrayList<Lanche> list = new ArrayList<Lanche>();
			try {
				comando = con.prepareStatement(ArquivosConfig.lancheSearchAtivos);
				ResultSet r = comando.executeQuery();
				while (r.next()) {
					Lanche lanche = getLancheFromResultSet(r);
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

	private Lanche getLancheFromResultSet(ResultSet r) throws SQLException {
		Date dtCadastro = null;
		Date dtModificacao = null;
		Timestamp tsCadastro = r.getTimestamp(4);
		Timestamp tsModificacao = r.getTimestamp(5);
		if(tsCadastro != null)
			dtCadastro = new Date(tsCadastro.getTime());
		if(tsModificacao != null)
			dtModificacao = new Date(tsModificacao.getTime());
		
		Lanche lanche = new Lanche(r.getInt(1), r.getString(2),
				r.getDouble(3), dtCadastro, //r.getDate(4),
				dtModificacao,r.getBoolean(6));

		OpcionaisDAO op = new OpcionaisDAO();
		lanche.setOpcionais(op.searchByLanche(lanche.getId()));
		return lanche;
	}
	
	@Override
	public boolean delete(Lanche t) {
		boolean ret = false;
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.lancheDelete);
				comando.setInt(1, t.getId());
				ret = comando.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;	
	}
	public boolean delete(int id) {
		Lanche l = new Lanche(id);		
		return delete(l);	
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
			comando = con.prepareStatement(ArquivosConfig.lancheInsert,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, t.getDescricao());
			comando.setDouble(2, t.getPreco());
			comando.setBoolean(3, t.isAtivo());
			
			if(comando.executeUpdate()>0){
				ResultSet r = comando.getGeneratedKeys();
				if(r.next())
					t.setId(r.getInt(1));
			}	
		}
	}

	private void update(Lanche t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.lancheUpdate);
			comando.setString(1, t.getDescricao());
			comando.setDouble(2, t.getPreco());
			comando.setBoolean(3, t.isAtivo());
			comando.setInt(4, t.getId());
			
			comando.executeUpdate();	
		}
		
	}

	public void updateOpcionais(int idLanche, ArrayList<Integer> idsOpcionais) throws SQLException {
		Lanche lanche = searchByID(idLanche);
		
		for (Opcionais opcional : lanche.getOpcionais()) {
			if(!idsOpcionais.contains(opcional.getId())){
				removeOpcionalFromLanche(idLanche,opcional.getId());
			}
		}
		
		for (Integer idOpcional : idsOpcionais) {
			if(!lanche.getOpcionais().contains(new Opcionais(idOpcional))){
				addOpcionalFromLanche(idLanche,idOpcional);
			}
		}
		
	}

	private void addOpcionalFromLanche(int idLanche, int idOpcional) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.lancheInsertOpcional);
			comando.setInt(1, idLanche);
			comando.setInt(2, idOpcional);
			comando.executeUpdate();
		}
		
	}

	private void removeOpcionalFromLanche(int idLanche, int idOpcional) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.lancheDeleteOpcional);
			comando.setInt(1, idLanche);
			comando.setInt(2, idOpcional);
			comando.executeUpdate();
		}
		
	}

}
