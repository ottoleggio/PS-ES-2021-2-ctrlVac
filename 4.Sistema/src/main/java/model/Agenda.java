package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agendas")
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date data;
	private String hora;
	private char situacao;
	private LocalDate data_situacao = LocalDate.now();
	private String observacoes;

	
	@ManyToOne
	@JoinColumn(name = "fk_funcionario_id")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "fk_vacina_id")
	private Vacina vacina;
	
	public Agenda() {
		super();
	}

	public Agenda(String data, String hora, String observacoes, Funcionario funcionario,
			Vacina vacina) {
		super();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 java.sql.Date dataS = null;
		try {
			dataS = new java.sql.Date( format.parse(data).getTime());
		} catch (ParseException e) {
		}
		
		this.data = dataS;
		this.hora = hora;
		this.situacao = 'A';
		this.observacoes = observacoes;
		this.funcionario = funcionario;
		this.vacina = vacina;
	}
	
	public Agenda(Date data, String hora, String observacoes, Funcionario funcionario,
			Vacina vacina) {
		super();
		
		this.data = data;
		this.hora = hora;
		this.situacao = 'A';
		this.observacoes = observacoes;
		this.funcionario = funcionario;
		this.vacina = vacina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(char situacao) {
		this.situacao = situacao;
	}

	public LocalDate getData_situacao() {
		return data_situacao;
	}

	public void setData_situacao(LocalDate data_situacao) {
		this.data_situacao = data_situacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

}
