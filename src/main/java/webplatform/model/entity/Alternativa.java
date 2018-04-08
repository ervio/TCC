package webplatform.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Alternativa generated by hbm2java
 */
@Entity
@Table(name = "ALTERNATIVA")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idAlternativa")
public class Alternativa implements java.io.Serializable {

	private Long idAlternativa;
	private Questao questao;
	private String resposta;
	private Boolean correta;
	private Set<ExercicioAlunoResposta> exercicioAlunoRespostas = new HashSet<ExercicioAlunoResposta>();

	public Alternativa() {
	}

	public Alternativa(Long idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	public Alternativa(Long idAlternativa, Questao questao, String resposta, Boolean correta,
			Set<ExercicioAlunoResposta> exercicioAlunoRespostas) {
		this.idAlternativa = idAlternativa;
		this.questao = questao;
		this.resposta = resposta;
		this.correta = correta;
		this.exercicioAlunoRespostas = exercicioAlunoRespostas;
	}

	@Id
	@GenericGenerator(name = "ALTERNATIVA_SEQUENCE", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "ALTERNATIVA_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "ALTERNATIVA_SEQUENCE")
	@Column(name = "ID_ALTERNATIVA", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getIdAlternativa() {
		return this.idAlternativa;
	}

	public void setIdAlternativa(Long idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QUESTAO")
	public Questao getQuestao() {
		return this.questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	@Column(name = "RESPOSTA", length = 100)
	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Column(name = "CORRETA")
	@Type(type = "yes_no")
	public Boolean getCorreta() {
		return this.correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alternativa")
	public Set<ExercicioAlunoResposta> getExercicioAlunoRespostas() {
		return this.exercicioAlunoRespostas;
	}

	public void setExercicioAlunoRespostas(Set<ExercicioAlunoResposta> exercicioAlunoRespostas) {
		this.exercicioAlunoRespostas = exercicioAlunoRespostas;
	}

}
