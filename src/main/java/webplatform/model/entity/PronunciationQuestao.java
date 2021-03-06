package webplatform.model.entity;
// default package

import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * PronunciationQuestao generated by hbm2java
 */
@Entity
@Table(name = "PRONUNCIATION_QUESTAO")
public class PronunciationQuestao implements java.io.Serializable {

	private Long id;
	private Exercicio exercicio;
	private Integer sequencia;
	private List<PronunciationQuestaoParte> pronunciationQuestaoPartes = new ArrayList<PronunciationQuestaoParte>();

	public PronunciationQuestao() {
	}

	public PronunciationQuestao(Long id) {
		this.id = id;
	}

	public PronunciationQuestao(Long id, Exercicio exercicio,
			ArrayList<PronunciationQuestaoParte> pronunciationQuestaoPartes) {
		this.id = id;
		this.exercicio = exercicio;
		this.pronunciationQuestaoPartes = pronunciationQuestaoPartes;
	}

	@Id
	@GenericGenerator(name = "PRONUNCIATION_QUESTAO_SEQUENCE", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "PRONUNCIATION_QUESTAO_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "PRONUNCIATION_QUESTAO_SEQUENCE")
	@Column(name = "ID_PRONUNCIATION_QUESTAO", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EXERCICIO")
	public Exercicio getExercicio() {
		return this.exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pronunciationQuestao")
	public List<PronunciationQuestaoParte> getPronunciationQuestaoPartes() {
		return pronunciationQuestaoPartes;
	}

	public void setPronunciationQuestaoPartes(List<PronunciationQuestaoParte> pronunciationQuestaoPartes) {
		this.pronunciationQuestaoPartes = pronunciationQuestaoPartes;
	}

	@Column(name = "SEQUENCIA", precision = 2, scale = 0)
	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}
}
