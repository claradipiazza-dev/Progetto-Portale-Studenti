package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Corsi;
import query.CorsoService;

@WebServlet("/api/corsi/*") // esempio: /api/corsi/1
public class CorsoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorsoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo(); // "/1"
        try {
            if (pathInfo != null && pathInfo.length() > 1) {
                int corsoId = Integer.parseInt(pathInfo.substring(1));
                Corsi corso = CorsoService.ottieniCorsoPerId(corsoId);

                if (corso != null) {
                    String json = String.format(
                        "{\"id\":%d,\"nome_corso\":\"%s\",\"livello\":\"%s\"}",
                        corso.getId(), corso.getNomeCorso(), corso.getLivello()
                    );
                    response.getWriter().write(json);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                // ritorna tutti i corsi
                Corsi wrapper = CorsoService.ottieniCorsi();
                List<Corsi> corsi = wrapper.getLista();
                StringBuilder json = new StringBuilder("[");
                for (Corsi c : corsi) {
                    json.append("{\"id\":").append(c.getId())
                        .append(",\"nome\":\"").append(c.getNomeCorso())
                        .append("\",\"livello\":\"").append(c.getLivello()).append("\"},");
                }
                if (!corsi.isEmpty()) json.setLength(json.length() - 1);
                json.append("]");
                response.getWriter().write(json.toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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

