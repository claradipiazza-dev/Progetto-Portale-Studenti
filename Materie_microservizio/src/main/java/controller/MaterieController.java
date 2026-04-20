package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Materie;
import query.MaterieService;

@WebServlet("/api/materie")
public class MaterieController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaterieController() {
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

        String insIdParam = request.getParameter("insId");

        if (insIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parametro insId mancante\"}");
            return;
        }

        try {
            int insId = Integer.parseInt(insIdParam);
			Materie wrapper = MaterieService.ottieniMaterie(insId);
            List<Materie> materie = wrapper.getLista();

            StringBuilder json = new StringBuilder("[");
            for (Materie m : materie) {
                json.append("{")
                    .append("\"id\":").append(m.getId()).append(",")
                    .append("\"anno\":").append(m.getAnno()).append(",")
                    .append("\"codice\":").append(m.getCodice()).append(",")
                    .append("\"nomeMateria\":\"").append(m.getNomeMateria()).append("\",")
                    .append("\"CFU\":").append(m.getCFU())
                    .append("},");
            }

            if (!materie.isEmpty())
                json.setLength(json.length() - 1);

            json.append("]");
            response.getWriter().write(json.toString());

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
