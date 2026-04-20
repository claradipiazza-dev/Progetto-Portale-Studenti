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

import org.json.JSONArray;
import org.json.JSONObject;

import dto.CorsiDTO;
import dto.InsegnamentiDTO;
import dto.UtenteDTO;

@WebServlet("/CorsiLaurea")
public class CorsiLaurea extends HttpServlet {

    private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorsiLaurea() {
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
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        UtenteDTO utente = (UtenteDTO) session.getAttribute("utente");

        String corsoIdParam = request.getParameter("corso_laurea_id");
        if (corsoIdParam == null) {
            response.sendRedirect("Home");
            return;
        }

        int corsoLaureaId = Integer.parseInt(corsoIdParam);

        InsegnamentiDTO listaInsegnamenti = new InsegnamentiDTO();
        CorsiDTO corso = null;

        // MICROSERVIZIO CORSI DI LAUREA

        String urlCorso =
                "http://localhost:8080/Corsi_microservizio/api/corsi/" + corsoLaureaId;

        HttpURLConnection connCorso =
                (HttpURLConnection) new URL(urlCorso).openConnection();
        connCorso.setRequestMethod("GET");

        if (connCorso.getResponseCode() == HttpURLConnection.HTTP_OK) {

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connCorso.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONObject obj = new JSONObject(json.toString());

            corso = new CorsiDTO(
                    obj.getInt("id"),
                    obj.getString("nome_corso"),
                    obj.getString("livello")
            );

            System.out.println("DEBUG CORSI ---- Corso trovato: " + corso.getNomeCorso());
        }

        connCorso.disconnect();

        // MICROSERVIZIO INSEGNAMENTI

        String urlInsegnamenti =
                "http://localhost:8080/Corsi_microservizio/api/insegnamenti?corsoId=" + corsoLaureaId;

        HttpURLConnection connIns =
                (HttpURLConnection) new URL(urlInsegnamenti).openConnection();
        connIns.setRequestMethod("GET");

        if (connIns.getResponseCode() == HttpURLConnection.HTTP_OK) {

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connIns.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray array = new JSONArray(json.toString());

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                InsegnamentiDTO ins = new InsegnamentiDTO(
                        obj.getInt("id"),
                        obj.getString("nome_ins"),
                        obj.getInt("corsoId")
                );

                listaInsegnamenti.setLista(ins);
            }

            System.out.println(
                "DEBUG CORSI ---- Insegnamenti trovati: " +
                listaInsegnamenti.getLista().size()
            );
        }

        connIns.disconnect();
        
        request.setAttribute("insegnamenti", listaInsegnamenti);
        request.setAttribute("livello", corso);
        request.setAttribute("utente", utente);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/insegnamenti.jsp");
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
