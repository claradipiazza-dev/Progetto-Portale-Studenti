package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utenti;
import query.UtenteService;


@WebServlet("/api/login")
public class UtenteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtenteController() {
		super();
		// TODO Auto-generated constructor stub
	}

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        if (email == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parametri mancanti\"}");
            return;
        }

        try {
        	
            String passwordHash = UtenteService.passwordHash(password);
            Utenti utente = UtenteService.accesso(email, passwordHash);
            
            System.out.println("EMAIL MS: " + email);
            System.out.println("PASSWORD MS: " + password);


            if (utente != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(
                	    "{ \"id\": " + utente.getId() +
                	    ", \"nome\": \"" + utente.getNome() + "\"" +
                	    ", \"cognome\": \"" + utente.getCognome() + "\"" +
                	    ", \"email\": \"" + utente.getEmail() + "\"" +
                	    ", \"numero\": \"" + utente.getNumero() + "\"" +
                	    ", \"matricola\": " + utente.getMatricola() +
                	    " }"
                	);

            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Credenziali errate\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
