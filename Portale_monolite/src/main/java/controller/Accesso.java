package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IscrizioneView;
//import model.Iscrizioni;
import model.Utenti;
import query.IscrizioneView_service;
//import query.Iscrizioni_service;
import query.Utenti_service;

/**
 * Servlet implementation class Accesso
 */
@WebServlet("/Accesso")

public class Accesso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accesso() {
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

		try {

			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String address = "";
			Utenti utente = (Utenti) session.getAttribute("utente");
			
			if (email == null || password == null) {
			    response.sendRedirect("index.jsp"); 
			    return;
			}

			// Hash della password fornita dall'utente
			String passwordHash = Utenti_service.passwordHash(password);
			utente = Utenti_service.accesso(email, passwordHash);

			if (utente != null) {
				System.out.println(utente.getNome());
				System.out.println(password);
				//Iscrizioni listaiscrizioni = Iscrizioni_service.ottieniIscrizioni(utente.getId());
				List<IscrizioneView> iscrizioni =IscrizioneView_service.ottieniIscrizioniConDettagli(utente.getId());
				
				//System.out.println("lista iscrizioni: " + listaiscrizioni.getLista().size());
				System.out.println("lista iscrizioni: " + iscrizioni.size());
				
				//session.setAttribute("iscrizioni", listaiscrizioni);
				session.setAttribute("iscrizioni", iscrizioni);
				session.setAttribute("utente", utente);
				session.setAttribute("isIscritto", iscrizioni != null && !iscrizioni.isEmpty());
				address = "/WEB-INF/view/utenteView.jsp";
			}

			else {

				address = "CredenzialiErrate";
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);


		} catch (SQLException | NamingException | NoSuchAlgorithmException | NullPointerException ex) {
			throw new ServletException(ex);
		}
	}

}
