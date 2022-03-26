package persistence;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import model.Login;

public class LoginDAO {
	private EntityManager em;

	public LoginDAO(EntityManager em){
		this.em = em;
	}

	
	public boolean checarLogin(String usuario, String senha) {
		try {
			
			String jpql = "SELECT u from Login u where u.usuario = :usuario and u.senha = :senha";
			em.createQuery(jpql, Login.class)
					.setParameter("usuario", usuario)
					.setParameter("senha", senha).getSingleResult();
			
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}
