package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Comorbidade;

public class ComorbidadeDAO {
	private EntityManager em;

	public ComorbidadeDAO(EntityManager em){
	this.em = em;
	}

	public void incluir(Comorbidade comorbidade) {
		this.em.persist(comorbidade);
	}
	
	public void alterar(Comorbidade comorbidade) {
		this.em.merge(comorbidade);
	}

	public void excluir(Comorbidade comorbidade) {
		comorbidade = this.em.merge(comorbidade);
		this.em.remove(comorbidade);
	}

	public Comorbidade buscaPorId(int id) {
		return this.em.find(Comorbidade.class, id);
	}

	public List<Comorbidade> listarTodos() {
		String jpql = "SELECT a FROM Comorbidade a";
		return em.createQuery(jpql, Comorbidade.class).getResultList();
	}
}
