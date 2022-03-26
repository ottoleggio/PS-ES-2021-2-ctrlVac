package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Funcionario;

public class FuncionarioDAO {
	private EntityManager em;

	public FuncionarioDAO(EntityManager em) {
		this.em = em;
	}

	public void incluir(Funcionario funcionario) {
		this.em.persist(funcionario);
	}

	public void alterar(Funcionario funcionario) {
		this.em.merge(funcionario);
	}

	public void excluir(Funcionario funcionario) {
		funcionario = this.em.merge(funcionario);
		this.em.remove(funcionario);
	}

	public Funcionario buscaPorId(int id) {
		return this.em.find(Funcionario.class, id);
	}

	public List<Funcionario> listarTodos() {
		String jpql = "SELECT u FROM Funcionario u";
		return em.createQuery(jpql, Funcionario.class).getResultList();
	}

	public List<Funcionario> buscarPorNome(String nome){
		String jpql = "SELECT u FROM Funcionario u WHERE u.nome = :nome";
		return em.createQuery(jpql, Funcionario.class)
		.setParameter("nome", nome)
		.getResultList();
		}
}
