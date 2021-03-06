package webplatform.model.entity;

// default package
// Generated May 9, 2017 11:21:55 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Convite generated by hbm2java
 */
@Entity
@Table(name = "CONVITE")
public class Convite implements java.io.Serializable {

	private ConviteId id;
	private Professor professor;
	private Aluno aluno;
	private Boolean aceito;

	public Convite() {
	}

	public Convite(ConviteId id, Professor professor, Aluno aluno) {
		this.id = id;
		this.professor = professor;
		this.aluno = aluno;
	}

	public Convite(ConviteId id, Professor professor, Aluno aluno, Boolean aceito) {
		this.id = id;
		this.professor = professor;
		this.aluno = aluno;
		this.aceito = aceito;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "professor", column = @Column(name = "PROFESSOR", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "aluno", column = @Column(name = "ALUNO", nullable = false, precision = 22, scale = 0)) })
	public ConviteId getId() {
		return this.id;
	}

	public void setId(ConviteId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROFESSOR", nullable = false, insertable = false, updatable = false)
	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ALUNO", nullable = false, insertable = false, updatable = false)
	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Type(type = "yes_no")
	@Column(name = "ACEITO", length = 1)
	public Boolean getAceito() {
		return this.aceito;
	}

	public void setAceito(Boolean aceito) {
		this.aceito = aceito;
	}

}
