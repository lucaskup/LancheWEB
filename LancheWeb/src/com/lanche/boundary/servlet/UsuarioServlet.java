package com.lanche.boundary.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanche.controller.UsuarioController;
import com.lanche.entity.Usuario;
import com.lanche.entity.enums.Funcao;
import com.lanche.utils.PasswordHash;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("METHOD");
		if(method.equalsIgnoreCase("DELETE")){
			String id = request.getParameter("id");
			UsuarioController c = new UsuarioController();
			c.delete(Integer.parseInt(id));
			
		}else if(method.equalsIgnoreCase("PUT")) {
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String pass = request.getParameter("password");
			try {
				pass = PasswordHash.createHash(pass);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | NullPointerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			String dtNascimento = request.getParameter("dtnascimento");
			String turma = request.getParameter("turma");
			String funcao = request.getParameter("funcao");
			String ativo = request.getParameter("ativo");
			
			try {
				UsuarioController c = new UsuarioController();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Usuario usu = new Usuario(Integer.parseInt(id),login,nome,sobrenome,new java.util.Date(format.parse(dtNascimento).getTime()),turma,Funcao.valueOf(funcao.toUpperCase()),pass,ativo.equalsIgnoreCase("1"));
				c.createOrUpdateLanche(usu);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		response.sendRedirect("usuario.jsp");
	}

}
