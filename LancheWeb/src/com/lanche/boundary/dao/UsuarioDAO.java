package com.lanche.boundary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lanche.entity.Usuario;
import com.lanche.entity.enums.Funcao;
import com.lanche.utils.ArquivosConfig;

public class UsuarioDAO extends DAO<Usuario> {

	@Override
	public Usuario searchByID(int id) {
		if (openConnection()) {
			Usuario usuario = null;

			try {
				comando = con.prepareStatement(ArquivosConfig.usuarioSearchByID);
				comando.setInt(1, id);
				ResultSet r = comando.executeQuery();
				if (r.next()) {
					usuario = new Usuario(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getDate(5),r.getString(6),Funcao.getFuncao(r.getInt(7)),r.getString(8),r.getBoolean(9));
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
			return usuario;
		}
		return null;
	}
	
	public Usuario searchByLogin(String login) {
		if (openConnection()) {
			Usuario usuario = null;
			try {
				
				comando = con.prepareStatement(ArquivosConfig.usuarioSearchByLogin);
				comando.setString(1, login);

				ResultSet r = comando.executeQuery();
				if (r.next())
					usuario = new Usuario(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getDate(5),r.getString(6),Funcao.getFuncao(r.getInt(7)),r.getString(8),r.getBoolean(9));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return usuario;
		}
		return null;
	}

	@Override
	public boolean delete(Usuario t) {
		boolean ret = false;
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.usuarioDelete);
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
	public boolean persist(Usuario t) {
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
	private void insert(Usuario t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.usuarioInsert,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, t.getLogin());
			comando.setString(2, t.getNome());
			comando.setString(3, t.getSobrenome());
			comando.setDate(4, new java.sql.Date(t.getDataNascimento().getTime()));
			comando.setString(5, t.getTurma());
			comando.setInt(6, t.getFuncao().getValor());
			comando.setBoolean(7, t.isAtivo());
			comando.setString(8, t.getPassword());
			if(comando.executeUpdate()>0){
				ResultSet r = comando.getGeneratedKeys();
				if(r.next())
					t.setId(r.getInt(1));
			}	
		}
		
	}

	private void update(Usuario t) throws SQLException {
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.usuarioUpdate);
			comando.setString(1, t.getLogin());
			comando.setString(2, t.getNome());
			comando.setString(3, t.getSobrenome());
			comando.setDate(4, new java.sql.Date(t.getDataNascimento().getTime()));
			comando.setString(5, t.getTurma());
			comando.setInt(6, t.getFuncao().getValor());
			comando.setBoolean(7, t.isAtivo());
			comando.setInt(8, t.getId());
			comando.executeUpdate();
		}
		
	}

	public static void main(String[] args) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.searchByLogin("lucas.kup");
		
		
	}

	public List<Usuario> getAll() {
		if (openConnection()) {
			Usuario usuario = null;
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			try {
				comando = con.prepareStatement(ArquivosConfig.usuarioSearchAll);
				ResultSet r = comando.executeQuery();
				while (r.next()) {
					usuario = new Usuario(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getDate(5),r.getString(6),Funcao.getFuncao(r.getInt(7)),r.getString(8),r.getBoolean(9));
					list.add(usuario);
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
		Usuario u = new Usuario(id);
		return delete(u);
	}

}
