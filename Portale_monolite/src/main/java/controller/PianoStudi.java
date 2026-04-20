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

import model.Materie;
import model.Utenti;
import query.Iscrizioni_service;
import query.Materie_service;

/**
 * Servlet implementation class ProfiloUtente
 */
@WebServlet("/PianoStudi")

public class PianoStudi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PianoStudi() {
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
		String ins_idString = request.getParameter("ins_id");
		int ins_id = Integer.parseInt(ins_idString);
		String address = "";

		try {
			
		if (utente != null) {
			
			Materie listamaterie = Materie_service.ottieniMaterie(ins_id);
			
			System.out.println(ins_id);
			
			boolean iscritto = Iscrizioni_service.checkIscrizione(utente.getId(), ins_id);
			
			request.setAttribute("iscritto", iscritto);
			session.setAttribute("materie", listamaterie);
			request.setAttribute("ins_id", ins_id);   // SERVE PER IL BOTTONE "ISCRIVITI"
			session.setAttribute("utente", utente);
			address = "/WEB-INF/view/pianoStudi.jsp";
				
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
