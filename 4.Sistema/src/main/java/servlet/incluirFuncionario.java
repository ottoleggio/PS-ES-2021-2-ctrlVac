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

import model.Comorbidade;
import model.Funcionario;
import persistence.ComorbidadeDAO;
import persistence.FuncionarioDAO;

/**
 * Servlet implementation class incluir
 */
@WebServlet("/incluirFuncionario")
public class incluirFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();

			request.setCharacterEncoding("utf-8");

			String nome = request.getParameter("nome");
			String data_nascimento = request.getParameter("data_nascimento");
			char sexo = request.getParameter("sexo").charAt(0);
			String logradouro = request.getParameter("logradouro");
			int numero = Integer.parseInt(request.getParameter("numero"));
			String setor = request.getParameter("setor");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String comorbidade = request.getParameter("comorbidade");


			Funcionario funcionario = new Funcionario(nome, data_nascimento, sexo, logradouro, numero, setor, cidade, uf);

			em.getTransaction().begin();
			new FuncionarioDAO(em).incluir(funcionario);
			em.getTransaction().commit();

			if(comorbidade != null) {


				ComorbidadeDAO aldao = new ComorbidadeDAO(em);
				Comorbidade al = new Comorbidade(comorbidade);

				em.getTransaction().begin();
				aldao.incluir(al);
				funcionario.adicionarComorbidade(al);
				em.getTransaction().commit();
			}

			String mensagem = URLEncoder.encode(funcionario.getNome() + " foi incluído com sucesso", "utf-8");

			response.sendRedirect("listarFuncionario?mensagem=" + mensagem); 
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}



}
