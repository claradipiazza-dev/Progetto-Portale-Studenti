package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utenti;
import query.UtenteService;
/**
 * Servlet implementation class DettagliUtenteController
 */
@WebServlet("/api/utenti/*")
public class DettagliUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliUtenteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Estrae l'ID dall'URL (es. /api/utenti/1 -> prende "1")
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID utente mancante\"}");
            return;
        }

        try {
            // Rimuoviamo lo slash iniziale per avere solo l'ID
            int id = Integer.parseInt(pathInfo.substring(1));
            
            // CHIAMA IL SERVICE (Assicurati di avere questo metodo in UtenteService)
            Utenti utente = UtenteService.getUtenteById(id);

            if (utente != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(
                    "{ \"id\": " + utente.getId() +
                    ", \"nome\": \"" + utente.getNome() + "\"" +
                    ", \"cognome\": \"" + utente.getCognome() + "\"" +
                    ", \"email\": \"" + utente.getEmail() + "\"" +
                    ", \"numero\": \"" + utente.getNumero() + "\"" +
                    ", \"matricola\": \"" + utente.getMatricola() + "\"" +
                    " }"
                );
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Utente non trovato\"}");
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID non valido\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
