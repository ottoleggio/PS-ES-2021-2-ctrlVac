package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "senha")
	private String senha;
	
	public Login() {
		super();
	}
	
	public Login(String usuario, String senha) {
	}

	public String getUsuario() {
		return usuario;
	}


	public String getSenha() {
		return senha;
	}

	
	
	

}
