package com.lanche.boundary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lanche.entity.Usuario;
import com.lanche.entity.enums.Funcao;
import com.lanche.utils.ArquivosConfig;

public class UsuarioDAO extends DAO<Usuario> {

	@Override
	public Usuario searchByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario searchByLogin(String login) {
		if (openConnection()) {
			Usuario usuario = null;
			try {
				
				comando = con.prepareStatement(arq
						.getProperty(ArquivosConfig.usuarioSearchByLogin));
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean persist(Usuario t) {
		// TODO Auto-generated method stub
		return false;
	}

}
