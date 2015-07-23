package com.lanche.boundary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanche.controller.OpcionaisController;
import com.lanche.entity.Opcionais;

/**
 * Servlet implementation class OpcionaisServlet
 */
@WebServlet("/OpcionaisServlet")
public class OpcionaisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpcionaisServlet() {
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
			OpcionaisController c = new OpcionaisController();
			c.delete(Integer.parseInt(id));
			
		}else {
			String id = request.getParameter("id");
			String descricao = request.getParameter("descricao");
			String preco = request.getParameter("preco");
			String permiteMaisQueUm = request.getParameter("permitemais");
			
			OpcionaisController c = new OpcionaisController();
			c.createOrUpdateLanche(new Opcionais(Integer.parseInt(id),descricao,Double.valueOf(preco),permiteMaisQueUm.equalsIgnoreCase("1")));	
		}
		response.sendRedirect("opcional.jsp");
		
	}

}
