package webplatform.model;

import java.util.List;

public class ReadingQuestaoModel {

	private Long id;
	private String pergunta;
	private Integer sequencia;
	private Long resposta;
	private List<ReadingAlternativaModel> readingAlternativas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<ReadingAlternativaModel> getReadingAlternativas() {
		return readingAlternativas;
	}

	public void setReadingAlternativas(List<ReadingAlternativaModel> readingAlternativas) {
		this.readingAlternativas = readingAlternativas;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public Long getResposta() {
		return resposta;
	}

	public void setResposta(Long resposta) {
		this.resposta = resposta;
	}

}
