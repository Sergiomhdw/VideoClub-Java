package com.hlc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlc.dao.UsuarioDAOBBDD;

/**
 * Servlet implementation class Servletregistro
 */
@WebServlet("/Servletregistro")
public class Servletregistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servletregistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDAOBBDD usuario = new UsuarioDAOBBDD();
		String nick = request.getParameter("nick");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String clave = request.getParameter("clave");
		String email = request.getParameter("email");
		String check = request.getParameter("check");
		boolean checkvalidator = false;
		if(check.equals("true")) {
			checkvalidator = true;
		}

		String ret = usuario.CrearUsuario(nick, nombre, apellido, clave, email,checkvalidator);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(ret);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
