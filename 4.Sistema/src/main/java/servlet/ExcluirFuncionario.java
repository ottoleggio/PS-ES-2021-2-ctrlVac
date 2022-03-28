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

import model.Funcionario;
import persistence.FuncionarioDAO;

/**
 * Servlet implementation class ListarCandidatos
 */
@WebServlet(name = "excluirFuncionario", urlPatterns = { "/excluirFuncionario" })
public class ExcluirFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();
			int parameter = Integer.parseInt(request.getParameter("codigo"));

			request.setCharacterEncoding("utf-8");
			FuncionarioDAO adao = new FuncionarioDAO(em);

			Funcionario funcionario = adao.buscaPorId(parameter);
			String nome = funcionario.getNome();
			em.getTransaction().begin();
			adao.excluir(funcionario);
			em.getTransaction().commit();

			String mensagem = URLEncoder.encode("Registro de " +nome + " foi cancelado com sucesso", "utf-8");
			response.sendRedirect("listarFuncionario?mensagem="+mensagem);
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}

}
