package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import dto.MateriaDTO;


@WebServlet("/PianoStudi")
public class PianoStudi extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PianoStudi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String insId = request.getParameter("insId");
        
        System.out.println("Ins_id: " + insId);

        if (insId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            URL url = new URL(
                "http://localhost:8080/Materie_microservizio/api/materie?insId=" + insId
            );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonResponse = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    jsonResponse.append(line);
                }
                
                System.out.println("JSON ricevuto: " + jsonResponse);


                JSONArray jsonArray = new JSONArray(jsonResponse.toString());
                List<MateriaDTO> materie = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    MateriaDTO m = new MateriaDTO();
                    m.setId(obj.getInt("id"));
                    m.setAnno(obj.getInt("anno"));
                    m.setCodice(obj.getInt("codice"));
                    m.setNomeMateria(obj.getString("nomeMateria"));
                    m.setCFU(obj.getInt("CFU"));

                    materie.add(m);
                }

                request.setAttribute("materie", materie);
                request.setAttribute("ins_id", insId);

                request.getRequestDispatcher("/WEB-INF/view/pianoStudi.jsp")
                       .forward(request, response);

            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
