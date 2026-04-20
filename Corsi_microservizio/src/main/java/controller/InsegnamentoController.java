package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Insegnamenti;
import query.InsegnamentoService;

@WebServlet("/api/insegnamenti")
public class InsegnamentoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsegnamentoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        System.out.println("DEBUG INSEGNAMENTI ---- richiesta ricevuta");

        System.out.println("DEBUG INSEGNAMENTI ---- query string: "
                + request.getQueryString());

        System.out.println("DEBUG INSEGNAMENTI ---- corsoId param: "
                + request.getParameter("corsoId"));

        String corsoIdParam = request.getParameter("corsoId");

        if (corsoIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parametro corsoId mancante\"}");
            return;
        }

        try {
            int corsoId = Integer.parseInt(corsoIdParam);

            // recupero insegnamenti dal service
            Insegnamenti wrapper = InsegnamentoService.ottieniInsegnamentiCorso(corsoId);

            List<Insegnamenti> lista = wrapper.getLista();

            // costruzione JSON array
            StringBuilder json = new StringBuilder("[");
            for (Insegnamenti i : lista) {
                json.append("{")
                    .append("\"id\":").append(i.getId()).append(",")
                    .append("\"nome_ins\":\"").append(i.getNomeInsegnamento()).append("\",")
                    .append("\"corsoId\":").append(i.getCorso())
                    .append("},");
            }

            // rimuove ultima virgola
            if (!lista.isEmpty()) {
                json.setLength(json.length() - 1);
            }

            json.append("]");
            response.getWriter().write(json.toString());

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"corsoId non valido\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Errore interno server\"}");
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
