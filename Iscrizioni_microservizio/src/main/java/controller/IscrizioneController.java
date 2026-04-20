package controller;

import java.io.IOException;
import java.time.LocalDate;

//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Iscrizioni;
import query.IscrizioniService;

@WebServlet("/api/iscrizioni")
public class IscrizioneController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IscrizioneController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws IOException {

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        String utenteIdParam = request.getParameter("utenteId");

	        if (utenteIdParam == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter()
	                    .write("{\"error\":\"Parametro utenteId mancante\"}");
	            return;
	        }

	        try {
	            int utenteId = Integer.parseInt(utenteIdParam);

	            Iscrizioni iscrizioni = IscrizioniService.ottieniIscrizioni(utenteId);

	            StringBuilder json = new StringBuilder("[");
	            
	            for (Iscrizioni i : iscrizioni.getLista()) {
	                json.append("{")
	                    .append("\"id_iscrizione\":").append(i.getId()).append(",")
	                    .append("\"utente_id\":").append(i.getUtentiId()).append(",")
	                    .append("\"insegnamento_id\":").append(i.getInsegnamentoId()).append(",")
	                    .append("\"stato\":\"").append(i.getStato()).append("\",")
	                    .append("\"data\":\"").append(i.getData()).append("\"")
	                    .append("},");
	            }

	            if (!iscrizioni.getLista().isEmpty()) {
	                json.setLength(json.length() - 1); // rimuove ultima virgola
	            }

	            json.append("]");

	            response.setStatus(HttpServletResponse.SC_OK);
	            response.getWriter().write(json.toString());

	        } catch (NumberFormatException e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter()
	                    .write("{\"error\":\"Parametro utenteId non valido\"}");
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter()
	                    .write("{\"error\":\"Errore nel recupero delle iscrizioni\"}");
	            e.printStackTrace();
	        }
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Parametri dal front-end
        	String azione = request.getParameter("azione");
        	int utenteId = Integer.parseInt(request.getParameter("utenteId"));
            int insegnamentoId = Integer.parseInt(request.getParameter("insegnamentoId"));
            
            System.out.println("Insegnamento id microservizio: " + insegnamentoId);
            System.out.println("azione microservizio: " + azione);

            // Controllo se l'utente è già iscritto
            if (azione.equals("crea")) {

                boolean esiste =
                    IscrizioniService.checkIscrizione(utenteId, insegnamentoId);
                
            if (esiste) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("{\"error\":\"Risulti già iscritto a questo corso\"}");
                return;
            }

            // Inserimento iscrizione principale
            IscrizioniService.inserisciIscrizione(
                LocalDate.now(),
                utenteId,
                insegnamentoId,
                "ATTIVA"
            );
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
           } 
            
            if (azione.equals("elimina")) {

               IscrizioniService.eliminaIscrizione(
                   utenteId,
                   insegnamentoId
               );

               response.setStatus(HttpServletResponse.SC_OK);
           }

            
            response.getWriter().write("{\"success\":\"Iscrizione completata\"}");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parametri non validi\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Errore interno server\"}");
            e.printStackTrace();
        }
    }
    
}
