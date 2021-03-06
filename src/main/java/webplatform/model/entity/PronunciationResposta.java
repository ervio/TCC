package webplatform.model.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PronunciationResposta generated by hbm2java
 */
@Entity
@Table(name = "PRONUNCIATION_RESPOSTA")
public class PronunciationResposta implements java.io.Serializable {

	private Long id;
	private ExercicioAluno exercicioAluno;
	private PronunciationQuestaoParte pronunciationQuestaoParte;
	private String resposta;

	public PronunciationResposta() {
	}

	public PronunciationResposta(Long id) {
		this.id = id;
	}

	public PronunciationResposta(Long id, ExercicioAluno exercicioAluno,
			PronunciationQuestaoParte pronunciationQuestaoParte, String resposta) {
		this.id = id;
		this.exercicioAluno = exercicioAluno;
		this.pronunciationQuestaoParte = pronunciationQuestaoParte;
		this.resposta = resposta;
	}

	@Id
	@GenericGenerator(name = "PRONUNCIATION_RESPOSTA_SEQUENC", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "PRONUNCIATION_RESPOSTA_SEQUENC"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "PRONUNCIATION_RESPOSTA_SEQUENC")
	@Column(name = "PRONUNCIATION_RESPOSTA_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXERCICIO_ALUNO")
	public ExercicioAluno getExercicioAluno() {
		return this.exercicioAluno;
	}

	public void setExercicioAluno(ExercicioAluno exercicioAluno) {
		this.exercicioAluno = exercicioAluno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRONUNCIATION_QUESTAO_PARTE")
	public PronunciationQuestaoParte getPronunciationQuestaoParte() {
		return this.pronunciationQuestaoParte;
	}

	public void setPronunciationQuestaoParte(PronunciationQuestaoParte pronunciationQuestaoParte) {
		this.pronunciationQuestaoParte = pronunciationQuestaoParte;
	}

	@Column(name = "RESPOSTA", length = 500)
	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

}
