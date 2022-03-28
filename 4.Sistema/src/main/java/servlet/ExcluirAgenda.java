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

import model.Agenda;
import persistence.AgendaDAO;

/**
 * Servlet implementation class ListarCandidatos
 */
@WebServlet(name = "excluirAgenda", urlPatterns = { "/excluirAgenda" })
public class ExcluirAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();
			int parameter = Integer.parseInt(request.getParameter("codigo"));

			request.setCharacterEncoding("utf-8");
			AgendaDAO adao = new AgendaDAO(em);

			Agenda agenda = adao.buscaPorId(parameter);
			em.getTransaction().begin();
			adao.excluir(agenda);
			em.getTransaction().commit();

			String mensagem = URLEncoder.encode("Agenda excluída", "utf-8");
			response.sendRedirect("listarAgenda?mensagem="+mensagem);
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}

}
