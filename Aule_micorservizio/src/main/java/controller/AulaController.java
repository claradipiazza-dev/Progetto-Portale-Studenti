package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aule;
import query.AuleService;

@WebServlet("/api/aule")
public class AulaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AulaController() {
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

        String matIdParam = request.getParameter("matId");
        System.out.println("Parametro materie id nel controller: " + matIdParam);

        if (matIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parametro matId mancante\"}");
            return;
        }

        try {
            int matId = Integer.parseInt(matIdParam);
            Aule wrapper = AuleService.ottieniAule(matId);
            List<Aule> aule = wrapper.getLista();

            StringBuilder json = new StringBuilder("[");
            for (Aule a : aule) {
                json.append("{")
                    .append("\"id\":").append(a.getId()).append(",")
                    .append("\"nome\":\"").append(a.getNomeAula()).append("\",")
                    .append("\"edificio\":").append(a.getEdificio()).append(",")
                    .append("\"matId\":").append(a.getMat_id())
                    .append("},");
            }

            if (!aule.isEmpty())
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
