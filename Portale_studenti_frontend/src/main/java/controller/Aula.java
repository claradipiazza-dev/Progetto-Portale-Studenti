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

import org.json.JSONArray;
import org.json.JSONObject;

import dto.AulaDTO;
import dto.IscrizioneDTO;
import dto.MateriaDTO;
import dto.UtenteDTO;

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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        UtenteDTO utente = (UtenteDTO) session.getAttribute("utente");

        List<IscrizioneDTO> listaIscrizioni = new ArrayList<>();
        List<MateriaDTO> listaMaterieFinale = new ArrayList<>();
        List<AulaDTO> listaAuleFinale = new ArrayList<>();

        // CHIAMATA MICROSERVIZIO ISCRIZIONI
         String urlIscrizioni =
                "http://localhost:8080/Iscrizioni_microservizio/api/iscrizioni?utenteId=" + utente.getId();

        HttpURLConnection connIscr =
                (HttpURLConnection) new URL(urlIscrizioni).openConnection();
        connIscr.setRequestMethod("GET");

        if (connIscr.getResponseCode() == HttpURLConnection.HTTP_OK) {

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connIscr.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray array = new JSONArray(json.toString());

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                IscrizioneDTO iscr = new IscrizioneDTO();
                iscr.setId(obj.getInt("id_iscrizione"));
                iscr.setUtentiId(obj.getInt("utente_id"));
                iscr.setInsegnamentoId(obj.getInt("insegnamento_id"));
                iscr.setStato(obj.getString("stato"));
                iscr.setData(
                        LocalDate.parse(obj.getString("data"))
                    );

                listaIscrizioni.add(iscr);
                System.out.println("DEBUG AULE ---- Iscrizioni trovate: " + listaIscrizioni.size());
                
            }
        }

        connIscr.disconnect();

        // PER OGNI ISCRIZIONE -> MICROSERVIZIO MATERIE
        for (IscrizioneDTO i : listaIscrizioni) {

            String urlMaterie =
                    "http://localhost:8080/Materie_microservizio/api/materie?insId=" + i.getInsegnamentoId();

            HttpURLConnection connMat =
                    (HttpURLConnection) new URL(urlMaterie).openConnection();
            connMat.setRequestMethod("GET");

            if (connMat.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connMat.getInputStream()));
                StringBuilder json = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }

                JSONArray array = new JSONArray(json.toString());

                for (int j = 0; j < array.length(); j++) {
                    JSONObject obj = array.getJSONObject(j);

                    MateriaDTO m = new MateriaDTO();
                    m.setId(obj.getInt("id"));
                    m.setAnno(obj.getInt("anno"));
                    m.setCodice(obj.getInt("codice"));
                    m.setNomeMateria(obj.getString("nomeMateria"));
                    m.setCFU(obj.getInt("CFU"));

                    listaMaterieFinale.add(m);
                    System.out.println("DEBUG AULE ---- Materie trovate: " + listaMaterieFinale.size());

                    // MICROSERVIZIO AULE
                    System.out.println("DEBUG AULE ---- Chiamo microservizio Aule con matId = " + m.getId());

                    String urlAule =
                            "http://localhost:8080/Aule_micorservizio/api/aule?matId=" + m.getId();
                    System.out.println("DEBUG: chiamo microservizio Aule con URL = " + urlAule);

                    HttpURLConnection connAule =
                            (HttpURLConnection) new URL(urlAule).openConnection();
                    connAule.setRequestMethod("GET");

                    if (connAule.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        BufferedReader r =
                                new BufferedReader(new InputStreamReader(connAule.getInputStream()));
                        StringBuilder jsonAule = new StringBuilder();
                        String l;

                        while ((l = r.readLine()) != null) {
                            jsonAule.append(l);
                        }

                        JSONArray arrAule = new JSONArray(jsonAule.toString());

                        for (int k = 0; k < arrAule.length(); k++) {
                            JSONObject o = arrAule.getJSONObject(k);

                            AulaDTO a = new AulaDTO();
                            a.setId(o.getInt("id"));
                            a.setNomeAula(o.getString("nome"));
                            a.setEdificio(o.getInt("edificio"));
                            a.setMat_id(o.getInt("matId"));

                            listaAuleFinale.add(a);
                            System.out.println("DEBUG AULE ---- Aule trovate: " + listaAuleFinale.size());
                            
                        }
                    }

                    connAule.disconnect();
                }
            }

            connMat.disconnect();
        }

        request.setAttribute("iscrizioni", listaIscrizioni);
        request.setAttribute("materie", listaMaterieFinale);
        request.setAttribute("aule", listaAuleFinale);
        request.setAttribute("utente", utente);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/aule.jsp");
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
