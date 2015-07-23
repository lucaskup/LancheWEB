package com.lanche.boundary.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanche.controller.LancheController;
import com.lanche.entity.Lanche;

/**
 * Servlet implementation class LancheServlet
 */
@WebServlet("/LancheServlet")
public class LancheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LancheServlet() {
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
		doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("METHOD");
		if(method.equalsIgnoreCase("DELETE")){
			String id = request.getParameter("id");
			LancheController c = new LancheController();
			c.delete(Integer.parseInt(id));
			
		}else {
			String id = request.getParameter("id");
			String descricao = request.getParameter("descricao");
			String preco = request.getParameter("preco");
			String ativo = request.getParameter("ativo");
			
			LancheController c = new LancheController();
			c.createOrUpdateLanche(new Lanche(Integer.parseInt(id),descricao,Double.valueOf(preco),ativo.equalsIgnoreCase("1")));

	
		}
		response.sendRedirect("lanche.jsp");
		
	}

}
