package webplatform.model;

import java.util.List;

public class GrammarDefinicaoModel {

	private Long id;
	private String definicao;
	private List<GrammarQuestaoModel> questoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDefinicao() {
		return definicao;
	}

	public void setDefinicao(String definicao) {
		this.definicao = definicao;
	}

	public List<GrammarQuestaoModel> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<GrammarQuestaoModel> questoes) {
		this.questoes = questoes;
	}

}
