package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Insegnamenti;
import query.InsegnamentoService;

@WebServlet("/api/insegnamenti/*")
public class InsegnamentiByIdController extends HttpServlet {

    private static final long serialVersionUID = 1L;
	
    /**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsegnamentiByIdController() {
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

        String pathInfo = request.getPathInfo(); // es: /1

        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(pathInfo.substring(1));

            Insegnamenti ins = InsegnamentoService.ottieniInsegnamentoPerId(id);

            if (ins == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            String json = "{"
                    + "\"id\":" + ins.getId() + ","
                    + "\"nome_ins\":\"" + ins.getNomeInsegnamento() + "\","
                    + "\"corsoId\":" + ins.getCorso()
                    + "}";

            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
