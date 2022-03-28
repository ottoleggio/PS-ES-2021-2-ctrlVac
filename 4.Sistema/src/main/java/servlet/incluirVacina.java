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
 * Servlet implementation class incluir
 */
@WebServlet("/incluirVacina")
public class incluirVacina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();

			request.setCharacterEncoding("utf-8");

			String titulo = request.getParameter("titulo");
			String descricao = request.getParameter("descricao");
			int doses = Integer.parseInt(request.getParameter("doses"));
			int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
			int intervalo = Integer.parseInt(request.getParameter("intervalo"));


			Vacina vacina = new Vacina(titulo, descricao, doses, periodicidade, intervalo);

			em.getTransaction().begin();
			new VacinaDAO(em).incluir(vacina);
			em.getTransaction().commit();
			String mensagem = URLEncoder.encode(vacina.getTitulo() + " foi incluído com sucesso", "utf-8");

			response.sendRedirect("listarVacina?mensagem=" + mensagem); 
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}



}
