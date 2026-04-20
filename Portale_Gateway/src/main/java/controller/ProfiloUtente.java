package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UtenteDTO;

import org.json.JSONObject;

@WebServlet("/ProfiloUtente")
public class ProfiloUtente extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfiloUtente() {
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

        // Controllo sessione e utente loggato
        if (session != null && session.getAttribute("utente") != null) {
            UtenteDTO utenteSession = (UtenteDTO) session.getAttribute("utente");

            try {
                // URL microservizio Utenti per ottenere dati aggiornati
                URL url = new URL("http://localhost:8080/Utenti_microservizio/api/utenti/" 
                                  + utenteSession.getId());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                int status = conn.getResponseCode();

                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder jsonResponse = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        jsonResponse.append(line);
                    }
                    in.close();

                    JSONObject json = new JSONObject(jsonResponse.toString());

                    // Creo DTO aggiornato
                    UtenteDTO utente = new UtenteDTO();
                    utente.setId(json.getInt("id"));
                    utente.setNome(json.getString("nome"));
                    utente.setCognome(json.optString("cognome", ""));
                    utente.setEmail(json.getString("email"));
                    utente.setNumero(json.optString("numero", ""));
                    utente.setMatricola(json.getString("matricola"));

                    // Metto in request per la JSP
                    request.setAttribute("utente", utente);
                    System.out.println("Profilo accettato");

                    RequestDispatcher dispatcher = 
                            request.getRequestDispatcher("/WEB-INF/view/profiloUtente.jsp");
                    dispatcher.forward(request, response);

                } else {
                    // Se microservizio non risponde, mantiene dati di sessione
                	System.out.println("Nessun profilo");
                    request.setAttribute("utente", utenteSession);
                    RequestDispatcher dispatcher = 
                            request.getRequestDispatcher("/WEB-INF/view/utenteView.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Sessione scaduta o utente non loggato
            response.sendRedirect("index.jsp");
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
