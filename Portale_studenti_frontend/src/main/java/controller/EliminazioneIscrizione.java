package controller;

import java.io.IOException;
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

@WebServlet("/EliminazioneIscrizione")
public class EliminazioneIscrizione extends HttpServlet {

    private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminazioneIscrizione() {
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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        UtenteDTO utente = (UtenteDTO) session.getAttribute("utente");
        String insegnamentoId = request.getParameter("ins_id");

        String body =
            "azione=elimina" +
            "&utenteId=" + utente.getId() +
            "&insegnamentoId=" + insegnamentoId;

        URL url = new URL(
            "http://localhost:8080/Iscrizioni_microservizio/api/iscrizioni"
        );

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty(
            "Content-Type", "application/x-www-form-urlencoded"
        );

        conn.getOutputStream().write(body.getBytes());

        int status = conn.getResponseCode();
        conn.disconnect();

        if (status == HttpServletResponse.SC_OK) {
            // ✔ Eliminazione riuscita
            RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                    "/WEB-INF/view/successoEliminazione.jsp"
                );
            dispatcher.forward(request, response);

        } else {
            // ❌ Errore eliminazione
            request.setAttribute(
                "errore",
                "Errore durante l'eliminazione dell'iscrizione"
            );
            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/aule.jsp");
            dispatcher.forward(request, response);
        }
    }
}
