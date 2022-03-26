package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Agenda;

public class AgendaDAO {
	private EntityManager em;

	public AgendaDAO(EntityManager em){
	this.em = em;
	}

	public void incluir(Agenda agenda) {
		this.em.persist(agenda);
	}
	
	public void alterar(Agenda agenda) {
		this.em.merge(agenda);
	}

	public void excluir(Agenda agenda) {
		agenda = this.em.merge(agenda);
		this.em.remove(agenda);
	}

	public Agenda buscaPorId(int id) {
		return this.em.find(Agenda.class, id);
	}

	public List<Agenda> listarTodos() {
		String jpql = "SELECT a FROM Agenda a";
		return em.createQuery(jpql, Agenda.class).getResultList();
	}
	
	public List<Agenda> listarCanceladas() {
		String jpql = "SELECT a FROM Agenda a WHERE a.situacao = 'C'";
		return em.createQuery(jpql, Agenda.class).getResultList();
	}
	
	public List<Agenda> listarRealizadas() {
		String jpql = "SELECT a FROM Agenda a WHERE a.situacao = 'R'";
		return em.createQuery(jpql, Agenda.class).getResultList();
	}
}
