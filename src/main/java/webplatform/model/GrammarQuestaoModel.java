package webplatform.model;

public class GrammarQuestaoModel {

	private Long id;
	private String questao;
	private GrammarDefinicaoModel definicaoResposta;
	private Integer resposta;
	private Integer sequencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}

	public GrammarDefinicaoModel getDefinicaoResposta() {
		return definicaoResposta;
	}

	public void setDefinicaoResposta(GrammarDefinicaoModel definicaoResposta) {
		this.definicaoResposta = definicaoResposta;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

}
