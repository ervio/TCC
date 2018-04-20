package webplatform.model;

public class GrammarQuestaoModel {

	private Long id;
	private String questao;
	private Long resposta;

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

	public Long getResposta() {
		return resposta;
	}

	public void setResposta(Long resposta) {
		this.resposta = resposta;
	}

}
