package servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comorbidade;
import persistence.ComorbidadeDAO;

/**
 * Servlet implementation class ListarCandidatos
 */
@WebServlet(name = "listarComorbidade", urlPatterns = { "/listarComorbidade" })
public class ListarComorbidade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");

			EntityManager em = fabrica.createEntityManager();

			request.setCharacterEncoding("utf-8");
			ComorbidadeDAO adao = new ComorbidadeDAO(em);

			List<Comorbidade> comorbidade = adao.listarTodos();

			request.setAttribute("comorbidade", comorbidade);

			String texto = request.getParameter("mensagem");
			String mensagem = "";
			if(texto != null) {
				mensagem = "?msg"+texto;
			}

			RequestDispatcher rd =  request.getRequestDispatcher("listarComorbidade.jsp" + mensagem);
			rd.forward(request, response);
		}
		catch(Exception e){
			response.sendRedirect("erro.html");
			
		}

	}

}
