package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.JSONArray;

import dto.UtenteDTO;
import dto.IscrizioneDTO;
import dto.IscrizioneView;

@WebServlet("/Home")
public class Home extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	public Home() {
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

        if (session != null && session.getAttribute("utente") != null) {

            UtenteDTO utenteSession = (UtenteDTO) session.getAttribute("utente");
            request.setAttribute("utente", utenteSession);

            List<IscrizioneDTO> listaIscrizioni = new ArrayList<>();

            try {
                // Recupera le iscrizioni dell'utente
                URL url = new URL(
                    "http://localhost:8080/Iscrizioni_microservizio/api/iscrizioni"
                    + "?utenteId=" + utenteSession.getId()
                );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder jsonResponse = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) jsonResponse.append(line);
                    in.close();

                    JSONArray array = new JSONArray(jsonResponse.toString());
                    session.setAttribute("isIscritto", array.length() > 0);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        IscrizioneDTO iscrizione = new IscrizioneDTO();
                        iscrizione.setId(obj.getInt("id_iscrizione"));
                        iscrizione.setUtentiId(obj.getInt("utente_id"));
                        iscrizione.setInsegnamentoId(obj.getInt("insegnamento_id"));
                        iscrizione.setStato(obj.getString("stato"));
                        iscrizione.setData(LocalDate.parse(obj.getString("data")));
                        listaIscrizioni.add(iscrizione);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            //  Per ogni iscrizione, recupera insegnamento e corso
            List<IscrizioneView> listaView = new ArrayList<>();
            for (IscrizioneDTO i : listaIscrizioni) {
                try {
                    // Recupera insegnamento
                    URL urlIns = new URL("http://localhost:8080/Corsi_microservizio/api/insegnamenti/" + i.getInsegnamentoId());
                    HttpURLConnection connIns = (HttpURLConnection) urlIns.openConnection();
                    connIns.setRequestMethod("GET");
                    connIns.setRequestProperty("Accept", "application/json");

                    JSONObject jsonIns = null;
                    if (connIns.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connIns.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null) sb.append(line);
                        in.close();
                        jsonIns = new JSONObject(sb.toString());
                    }

                    String nomeInsegnamento = jsonIns.getString("nome_ins");
                    int corsoId = jsonIns.getInt("corsoId");

                    // Recupera corso
                    URL urlCorso = new URL("http://localhost:8080/Corsi_microservizio/api/corsi/" + corsoId);
                    HttpURLConnection connCorso = (HttpURLConnection) urlCorso.openConnection();
                    connCorso.setRequestMethod("GET");
                    connCorso.setRequestProperty("Accept", "application/json");

                    JSONObject jsonCorso = null;
                    if (connCorso.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connCorso.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null) sb.append(line);
                        in.close();
                        jsonCorso = new JSONObject(sb.toString());
                    }

                    String nomeCorso = jsonCorso.getString("nome_corso");
                    String livello = jsonCorso.getString("livello");

                    // Crea DTO IscrizioneView
                    IscrizioneView view = new IscrizioneView(
                        i.getId(),
                        i.getData(),
                        i.getStato(),
                        nomeInsegnamento,
                        nomeCorso,
                        livello
                    );

                    listaView.add(view);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Inserisco il DTO completo in request
            request.setAttribute("iscrizioni", listaView);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/utenteView.jsp");
            dispatcher.forward(request, response);

        } else {
            // sessione non valida
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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
