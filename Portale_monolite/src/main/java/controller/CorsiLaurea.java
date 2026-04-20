package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Insegnamenti;
import model.Utenti;
import query.CorsiLaurea_service;
import query.Insegnamenti_service;
import model.Corsi;

/**
 * Servlet implementation class ProfiloUtente
 */
@WebServlet("/CorsiLaurea")

public class CorsiLaurea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorsiLaurea() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		doPost(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utenti utente = (Utenti) session.getAttribute("utente");
		String corso_laurea_idString = request.getParameter("corso_laurea_id");
		int corso_laurea_id = Integer.parseInt(corso_laurea_idString);
		String address = "";

		try {
			
		if (utente != null) {
			
			Insegnamenti lista_insegnamenti = Insegnamenti_service.ottieniInsegnamenti(corso_laurea_id);
			
			System.out.println(corso_laurea_id);
			
			Corsi livello = CorsiLaurea_service.ottieniLivello(corso_laurea_id);
			System.out.println(livello.getLivello());

			
			request.setAttribute("insegnamenti", lista_insegnamenti);
			request.setAttribute("livello", livello);
			request.setAttribute("corso_laurea_id", corso_laurea_id);   
			session.setAttribute("utente", utente);
			address = "/WEB-INF/view/insegnamenti.jsp";
				
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);

	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	}
}
