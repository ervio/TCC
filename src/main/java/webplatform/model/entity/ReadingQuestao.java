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
 * ReadingQuestao generated by hbm2java
 */
@Entity
@Table(name = "READING_QUESTAO")
public class ReadingQuestao implements java.io.Serializable {

	private Long id;
	private Exercicio exercicio;
	private String pergunta;
	private Integer sequencia;
	private List<ReadingAlternativa> readingAlternativas = new ArrayList<ReadingAlternativa>();

	public ReadingQuestao() {
	}

	public ReadingQuestao(Long id) {
		this.id = id;
	}

	public ReadingQuestao(Long id, Exercicio exercicio, String pergunta,
			ArrayList<ReadingAlternativa> readingAlternativas) {
		this.id = id;
		this.exercicio = exercicio;
		this.pergunta = pergunta;
		this.readingAlternativas = readingAlternativas;
	}

	@Id
	@GenericGenerator(name = "READING_QUESTAO_SEQUENCE", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "READING_QUESTAO_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "READING_QUESTAO_SEQUENCE")
	@Column(name = "ID_READING_QUESTAO", unique = true, nullable = false, precision = 22, scale = 0)
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

	@Column(name = "PERGUNTA", length = 500)
	public String getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questao")
	public List<ReadingAlternativa> getReadingAlternativas() {
		return readingAlternativas;
	}

	public void setReadingAlternativas(List<ReadingAlternativa> readingAlternativas) {
		this.readingAlternativas = readingAlternativas;
	}

	@Column(name = "SEQUENCIA", precision = 2, scale = 0)
	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

}
