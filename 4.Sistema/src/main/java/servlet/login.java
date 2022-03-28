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

import persistence.LoginDAO;

/**
 * Servlet implementation class incluir
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();

			request.setCharacterEncoding("utf-8");

			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");


			LoginDAO ldao = new LoginDAO(em);
			boolean check = ldao.checarLogin(usuario, senha);


			if(check) {
				response.sendRedirect("index.jsp");
			}
			else {
				String mensagem = URLEncoder.encode("Usuário ou senha não existe", "utf-8");
				response.sendRedirect("login.jsp?mensagem=" + mensagem); 

			}


		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}

	}



}
