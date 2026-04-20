package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Iscrizioni;
import model.Utenti;
import query.Iscrizioni_service;

/**
 * Servlet implementation class EliminaCarta
 */
@WebServlet("/EliminaIscrizione")
public class EliminaIscrizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaIscrizione() {
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
			Utenti utente = (Utenti) session.getAttribute("utente");
			int ins_id = Integer.parseInt(request.getParameter("ins_id"));
			
			Iscrizioni_service.eliminaIscrizione(utente.getId(), ins_id);
			Iscrizioni iscrizione = Iscrizioni_service.ottieniIscrizioni(utente.getId());
			
            request.setAttribute("ins_id", ins_id);

			session.setAttribute("iscrizione", iscrizione);
			session.setAttribute("utente", utente);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/successoEliminazione.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | NamingException | NoSuchAlgorithmException | NullPointerException ex) {
			throw new ServletException(ex);
		}

	}

}
