package webplatform.model;

import java.util.List;

public class QuestaoModel {

	private long idQuestao;
	private String pergunta;
	private String resposta;
	private String valorNota;
	private List<AlternativaModel> alternativas;

	public long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getValorNota() {
		return valorNota;
	}

	public void setValorNota(String valorNota) {
		this.valorNota = valorNota;
	}

	public List<AlternativaModel> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<AlternativaModel> alternativas) {
		this.alternativas = alternativas;
	}

}
