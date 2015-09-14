package com.lanche.boundary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanche.controller.PedidoController;

/**
 * Servlet implementation class PedidoServlet
 */
@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("METHOD");
		
		if(method.equalsIgnoreCase("PRONTO")){
			String id = request.getParameter("id");
			PedidoController ped  = new PedidoController();
			ped.passarParaPronto(Integer.parseInt(id));
			response.sendRedirect("pedido.jsp");
		}else if(method.equalsIgnoreCase("FAZENDO")) {
			String id = request.getParameter("id");
			PedidoController ped  = new PedidoController();
			ped.passarParaFazendo(Integer.parseInt(id));
			response.sendRedirect("pedido.jsp");
		}else if(method.equalsIgnoreCase("CRIAR")) {
			String json = request.getParameter("pedido");
			PedidoController ped  = new PedidoController();
			ped.criarPedidoJson(json);
			response.sendRedirect("pedido.jsp");
		} else if(method.equalsIgnoreCase("CRIARPED")) {
			response.sendRedirect("novo_pedido.jsp");
		}
		
	}

}
