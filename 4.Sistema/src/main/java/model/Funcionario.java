package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	@Column(name = "data_nascimento")
	private Date data_nascimento;
	private char sexo;
	private String logradouro;
	private int numero;
	private String setor;
	private String cidade;
	private String uf;

	@ManyToMany
	@JoinTable(name = "ass_funcionario_comorbidade",
				joinColumns = @JoinColumn(name = "fk_funcionario_id"),
				inverseJoinColumns = @JoinColumn(name = "fk_comorbidade_id"))
	private List<Comorbidade> comorbidades = new ArrayList<>();

	public void adicionarComorbidade(Comorbidade a) {
		this.comorbidades.add(a);
	}
	
	public void removerComorbidade(Comorbidade a) {
		this.comorbidades.remove(a);
	}
	
	public List<Comorbidade> obterComorbidades(){
		return this.comorbidades;
	}

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String data_nascimento, char sexo, String logradouro, int numero, String setor,
			String cidade, String uf) {
		super();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date data = null;
		try {
			data = new java.sql.Date(format.parse(data_nascimento).getTime());
		} catch (ParseException e) {
		}

		this.nome = nome;
		this.data_nascimento = data;
		this.sexo = sexo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.setor = setor;
		this.cidade = cidade;
		this.uf = uf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSexoDescritivo() {
		String descricao = "Masculino";
		if (this.sexo == 'F')
			descricao = "Feminino";
		return descricao;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", data_nascimento=" + data_nascimento + ", sexo=" + sexo
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", setor=" + setor + ", cidade=" + cidade
				+ ", uf=" + uf + "]";
	}

}