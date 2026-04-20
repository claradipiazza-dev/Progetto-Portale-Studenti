package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utenti;
import model.IscrizioneView;
import model.Materie;
import query.IscrizioneView_service;
import query.Iscrizioni_service;
import query.Materie_service;

/**
 * Servlet implementation class InviaPrenotazione
 */
@WebServlet("/IscrizioneCorso")
public class IscrizioneCorso extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IscrizioneCorso() {
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

        int ins_id = Integer.parseInt(request.getParameter("InsId"));
        String address;

        try {
            boolean esiste = Iscrizioni_service.checkIscrizione(utente.getId(), ins_id);

            if (!esiste) {
                LocalDate data = LocalDate.now();
                String stato = "ATTIVA";

                Iscrizioni_service.inserisciIscrizione(data, utente.getId(), ins_id, stato);

                // Recupero dati per la view utente
                Materie materie = Materie_service.ottieniMaterie(ins_id);
                
             // AGGIORNA IL FLAG IN SESSIONE 
                session.setAttribute("isIscritto", true); 
                
                // Ricarica anche la lista aggiornata per la sessione (opzionale ma consigliato)
                List<IscrizioneView> iscrizioni = IscrizioneView_service.ottieniIscrizioniConDettagli(utente.getId());
                session.setAttribute("iscrizioni", iscrizioni);

                request.setAttribute("materie", materie);
                request.setAttribute("ins_id", ins_id);

                address = "/WEB-INF/view/successoIscrizione.jsp";

            } else {
                request.setAttribute("errore", "Risulti già iscritto a questo corso");
                address = "/WEB-INF/view/pianoStudi.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}


