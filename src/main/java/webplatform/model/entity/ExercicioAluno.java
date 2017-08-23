package webplatform.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ExercicioAluno generated by hbm2java
 */
@Entity
@Table(name = "EXERCICIO_ALUNO")
public class ExercicioAluno implements java.io.Serializable {

	private Long idExercicioAluno;
	private Aluno aluno;
	private Exercicio exercicio;
	private Integer nota;
	private Integer chances;
	private Date dataInicio;
	private Date dataFim;
	private Set<ExercicioAlunoResposta> exercicioAlunoRespostas = new HashSet<ExercicioAlunoResposta>();

	public ExercicioAluno() {
	}

	public ExercicioAluno(Long idExercicioAluno) {
		this.idExercicioAluno = idExercicioAluno;
	}

	public ExercicioAluno(Long idExercicioAluno, Aluno aluno, Exercicio exercicio, Integer nota, Date dataInicio,
			Set<ExercicioAlunoResposta> exercicioAlunoRespostas) {
		this.idExercicioAluno = idExercicioAluno;
		this.aluno = aluno;
		this.exercicio = exercicio;
		this.nota = nota;
		this.dataInicio = dataInicio;
		this.exercicioAlunoRespostas = exercicioAlunoRespostas;
	}

	@Id
	@SequenceGenerator(name = "EXERCICIO_ALUNO_SEQUENCE", sequenceName = "EXERCICIO_ALUNO_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EXERCICIO_ALUNO_SEQUENCE")
	@Column(name = "ID_EXERCICIO_ALUNO", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getIdExercicioAluno() {
		return this.idExercicioAluno;
	}

	public void setIdExercicioAluno(Long idExercicioAluno) {
		this.idExercicioAluno = idExercicioAluno;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ALUNO")
	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EXERCICIO")
	public Exercicio getExercicio() {
		return this.exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	@Column(name = "NOTA", precision = 2, scale = 0)
	public Integer getNota() {
		return this.nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	@Column(name = "CHANCES", precision = 5, scale = 0)
	public Integer getChances() {
		return chances;
	}

	public void setChances(Integer chances) {
		this.chances = chances;
	}

	@Column(name = "DATA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Column(name = "DATA_FIM")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exercicioAluno")
	public Set<ExercicioAlunoResposta> getExercicioAlunoRespostas() {
		return this.exercicioAlunoRespostas;
	}

	public void setExercicioAlunoRespostas(Set<ExercicioAlunoResposta> exercicioAlunoRespostas) {
		this.exercicioAlunoRespostas = exercicioAlunoRespostas;
	}

}
