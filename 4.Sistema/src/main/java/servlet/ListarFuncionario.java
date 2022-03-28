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

import model.Funcionario;
import persistence.FuncionarioDAO;

/**
 * Servlet implementation class ListarCandidatos
 */
@WebServlet(name = "listarFuncionario", urlPatterns = { "/listarFuncionario" })
public class ListarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();

			request.setCharacterEncoding("utf-8");
			FuncionarioDAO adao = new FuncionarioDAO(em);
			List<Funcionario> funcionario = adao.listarTodos();

			request.setAttribute("funcionario", funcionario);

			String texto = request.getParameter("mensagem");
			String mensagem = "";
			if(texto != null) {
				mensagem = "?msg"+texto;
			}

			RequestDispatcher rd =  request.getRequestDispatcher("listarFuncionario.jsp" + mensagem);
			rd.forward(request, response);
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}

}
