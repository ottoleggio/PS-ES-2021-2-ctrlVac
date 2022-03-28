package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Vacina;
import persistence.VacinaDAO;

/**
 * Servlet implementation class ListarCandidatos
 */
@WebServlet(name = "excluirVacina", urlPatterns = { "/excluirVacina" })
public class ExcluirVacina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();
			int parameter = Integer.parseInt(request.getParameter("codigo"));

			request.setCharacterEncoding("utf-8");
			VacinaDAO adao = new VacinaDAO(em);

			Vacina vacina = adao.buscaPorId(parameter);
			em.getTransaction().begin();
			adao.excluir(vacina);
			em.getTransaction().commit();

			String mensagem = URLEncoder.encode("Vacina excluída", "utf-8");
			response.sendRedirect("listarVacina?mensagem="+mensagem);
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}

}
