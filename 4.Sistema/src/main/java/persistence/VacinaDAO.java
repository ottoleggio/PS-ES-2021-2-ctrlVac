package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Vacina;

public class VacinaDAO {
	private EntityManager em;

	public VacinaDAO(EntityManager em){
	this.em = em;
	}

	public void incluir(Vacina vacina) {
		this.em.persist(vacina);
	}
	
	public void alterar(Vacina vacina) {
		this.em.merge(vacina);
	}

	public void excluir(Vacina vacina) {
		vacina = this.em.merge(vacina);
		this.em.remove(vacina);
	}
	
	public Vacina buscaPorId(int id) {
		return this.em.find(Vacina.class, id);
	}

	public List<Vacina> listarTodos() {
		String jpql = "SELECT v FROM Vacina v";
		return em.createQuery(jpql, Vacina.class).getResultList();
	}
}
