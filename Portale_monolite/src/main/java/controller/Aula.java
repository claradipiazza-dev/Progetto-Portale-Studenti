package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Materie;
import model.Utenti;
import query.Aule_service;
import query.Iscrizioni_service;
import query.Materie_service;
import model.Aule;
import model.Iscrizioni;

/**
 * Servlet implementation class ProfiloUtente
 */
@WebServlet("/Aula")
public class Aula extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Aula() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utenti utente = (Utenti) session.getAttribute("utente");
        
        System.out.println("Idutente: " + utente.getId());

        String address = null;

        if (utente != null) {

        	Iscrizioni iscrizioni = Iscrizioni_service.ottieniIscrizioni(utente.getId());
        	
         	if (iscrizioni.getLista().isEmpty()) {
        	    System.out.println("Nessuna iscrizione trovata");
        	} else {
        	    System.out.println("Iscrizioni trovate: " + iscrizioni.getLista().size());
        	}

            List<Materie> listaMaterieFinale = new ArrayList<>();
            List<Aule> listaAuleFinale = new ArrayList<>();

            // Per ogni iscrizione
            for (Iscrizioni i : iscrizioni.getLista()) {

                Materie materie =  Materie_service.ottieniMaterie(i.getInsegnamentoId());

                // Per ogni materia
                for (Materie m : materie.getLista()) {

                    listaMaterieFinale.add(m);

                    Aule aule = Aule_service.ottieniAule(m.getId());
                    listaAuleFinale.addAll(aule.getLista());
                }
            }

            request.setAttribute("iscrizioni", iscrizioni);
            request.setAttribute("materie", listaMaterieFinale);
            request.setAttribute("aule", listaAuleFinale);
            request.setAttribute("utente", utente);

            address = "/WEB-INF/view/aule.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
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

