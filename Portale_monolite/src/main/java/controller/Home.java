package controller;

import java.io.IOException;
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

@WebServlet("/Home")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Utenti utente = (Utenti) session.getAttribute("utente");
        // Controllo se l'utente è loggato
        try {
        if (session != null && session.getAttribute("utente") != null) {
            //Iscrizioni iscrizioni = Iscrizioni_service.ottieniIscrizioni(utente.getId());
        	List<IscrizioneView> iscrizioni =IscrizioneView_service.ottieniIscrizioniConDettagli(utente.getId());
            
            String address = "/WEB-INF/view/utenteView.jsp";
            
            session.setAttribute("iscrizioni", iscrizioni);
            session.setAttribute("isIscritto", iscrizioni != null && !iscrizioni.isEmpty());
            // Se loggato, mostra la vista utente
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } else {
            // Se non è loggato, rimandato alla index
            response.sendRedirect("index.jsp"); 
        }
        } catch (SQLException | NamingException | NullPointerException ex) {
			throw new ServletException(ex);	
        }
  }
        
        

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}