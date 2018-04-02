package webplatform.model.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * GrammarQuestao generated by hbm2java
 */
@Entity
@Table(name = "GRAMMAR_QUESTAO")
public class GrammarQuestao implements java.io.Serializable {

	private Long id;
	private String questao;
	private GrammarDefinicao definicaoResposta;

	public GrammarQuestao() {
	}

	public GrammarQuestao(Long id) {
		this.id = id;
	}

	public GrammarQuestao(Long id, String questao, GrammarDefinicao definicaoResposta) {
		this.id = id;
		this.questao = questao;
		this.definicaoResposta = definicaoResposta;
	}

	@Id
	@SequenceGenerator(name = "GRAMMAR_QUESTAO_SEQUENCE", sequenceName = "GRAMMAR_QUESTAO_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GRAMMAR_QUESTAO_SEQUENCE")
	@Column(name = "ID_GRAMMAR_QUESTAO", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "QUESTAO", length = 500)
	public String getQuestao() {
		return this.questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEFINICAO_RESPOSTA")
	public GrammarDefinicao getDefinicaoResposta() {
		return this.definicaoResposta;
	}

	public void setDefinicaoResposta(GrammarDefinicao definicaoResposta) {
		this.definicaoResposta = definicaoResposta;
	}

}
