package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import model.Agenda;
import model.Funcionario;
import model.Vacina;
import persistence.AgendaDAO;
import persistence.FuncionarioDAO;
import persistence.VacinaDAO;

/**
 * Servlet implementation class incluir
 */
@WebServlet("/incluirAgenda")
public class incluirAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
			EntityManager em = fabrica.createEntityManager();

			request.setCharacterEncoding("utf-8");

			String data = request.getParameter("data");
			String hora = request.getParameter("hora");
			String observacoes = request.getParameter("observacoes");
			int userid = Integer.parseInt(request.getParameter("funcionario"));
			int vacid = Integer.parseInt(request.getParameter("vacina"));

			FuncionarioDAO udao = new FuncionarioDAO(em);
			Funcionario funcionario = udao.buscaPorId(userid);

			VacinaDAO vdao = new VacinaDAO(em);
			Vacina vacina = vdao.buscaPorId(vacid);

			Agenda agenda = new Agenda(data, hora, observacoes, funcionario, vacina);

			em.getTransaction().begin();
			new AgendaDAO(em).incluir(agenda);
			em.getTransaction().commit();

			if(vacina.getDoses() == 2) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				java.sql.Date dataS = null;
				try {
					dataS = new java.sql.Date( format.parse(data).getTime());
				} catch (ParseException e) {
				}

				DateTime dtOrg = new DateTime(dataS);

				switch(vacina.getPeriodicidade()) {
				case 1:
					dtOrg = dtOrg.plusDays(vacina.getIntervalo());
					break;
				case 2:
					dtOrg = dtOrg.plusWeeks(vacina.getIntervalo());
					break;
				case 3:
					dtOrg = dtOrg.plusMonths(vacina.getIntervalo());
					break;
				case 4:
					dtOrg = dtOrg.plusYears(vacina.getIntervalo());
					break;
				}

				Agenda agenda2 = new Agenda(dtOrg.toString("dd/MM/yyyy"), hora, observacoes, funcionario, vacina);

				em.getTransaction().begin();
				new AgendaDAO(em).incluir(agenda2);
				em.getTransaction().commit();

			}

			String mensagem = URLEncoder.encode(funcionario.getNome() + " foi incluído com sucesso", "utf-8");

			response.sendRedirect("listarAgenda?mensagem=" + mensagem);
		}
		catch(Exception e){
			response.sendRedirect("erro.html");

		}
	}



}
