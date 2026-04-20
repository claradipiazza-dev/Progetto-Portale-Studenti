package controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UtenteDTO;
import java.io.IOException;

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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        UtenteDTO utente = (UtenteDTO) session.getAttribute("utente");
        String insegnamentoId = request.getParameter("InsId");
        System.out.println("Insegnamento id gateway: " + insegnamentoId);
        String azione = request.getParameter("azione"); // "crea"

        String body = "azione=" + URLEncoder.encode(azione, "UTF-8") + 
        			"&utenteId=" + URLEncoder.encode(String.valueOf(utente.getId()), "UTF-8") +
                      "&insegnamentoId=" + URLEncoder.encode(insegnamentoId, "UTF-8"); 

        System.out.println("Body POST verso microservizio: " + body);


        try {
            URL url = new URL("http://localhost:8080/Iscrizioni_microservizio/api/iscrizioni");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }

            int status = conn.getResponseCode();

            if (status == HttpURLConnection.HTTP_CREATED) {
            	// AGGIORNA IL FLAG IMMEDIATAMENTE
                session.setAttribute("isIscritto", true);
                // Forward alla JSP di successo
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/successoIscrizione.jsp");
                dispatcher.forward(request, response);

            } else if (status == HttpURLConnection.HTTP_CONFLICT) {
                request.setAttribute("errore", "Risulti già iscritto a questo corso");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/pianoStudi.jsp");
                dispatcher.forward(request, response);

            } else {
                request.setAttribute("errore", "Errore durante l'iscrizione");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/pianoStudi.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore interno server");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/pianoStudi.jsp");
            dispatcher.forward(request, response);
        }
    }
}
