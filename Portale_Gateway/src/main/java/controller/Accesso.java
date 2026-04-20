package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dto.UtenteDTO;

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
        doPost(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // 1. CHIAMATA AL MICROSERVIZIO UTENTI PER IL LOGIN
            URL url = new URL("http://localhost:8080/Utenti_microservizio/api/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String body = "email=" + URLEncoder.encode(email, "UTF-8")
                        + "&password=" + URLEncoder.encode(password, "UTF-8");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }

            int status = conn.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    responseBody.append(line);
                }
                in.close();

                JSONObject json = new JSONObject(responseBody.toString());

                // 2. CREAZIONE DTO UTENTE
                UtenteDTO utente = new UtenteDTO();
                utente.setId(json.getInt("id"));
                utente.setNome(json.getString("nome"));
                utente.setEmail(json.getString("email"));
                utente.setNumero(json.optString("numero", "")); 
                utente.setMatricola(json.optString("matricola", "")); 
                utente.setCognome(json.optString("cognome", ""));

                // 3. AGGIUNTA: CONTROLLO ISCRIZIONI
                boolean haIscrizioni = false;
                try {
                    URL urlIsc = new URL("http://localhost:8080/Iscrizioni_microservizio/api/iscrizioni?utenteId=" + utente.getId());
                    HttpURLConnection connIsc = (HttpURLConnection) urlIsc.openConnection();
                    connIsc.setRequestMethod("GET");
                    connIsc.setRequestProperty("Accept", "application/json");

                    if (connIsc.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader inIsc = new BufferedReader(new InputStreamReader(connIsc.getInputStream()));
                        String inputLine = inIsc.readLine(); 
                        
                        // Se il JSON ricevuto non è un array vuoto "[]", l'utente ha iscrizioni attive
                        if (inputLine != null && inputLine.length() > 2) { 
                            haIscrizioni = true;
                        }
                        inIsc.close();
                    }
                } catch (Exception e) { 
                    System.err.println("Errore durante il recupero iscrizioni nel Login: " + e.getMessage());
                }

                // 4. SALVATAGGIO IN SESSIONE
                HttpSession session = request.getSession(true);
                session.setAttribute("utente", utente);
                session.setAttribute("isIscritto", haIscrizioni); // Flag per la Navbar

                // Redirect verso Home
                response.sendRedirect(request.getContextPath() + "/Home");

            } else {
                request.getRequestDispatcher("/WEB-INF/view/credenzialiErrate.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}