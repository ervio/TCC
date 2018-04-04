package webplatform.model;

import java.util.List;

public class ReadingQuestaoModel {

	private Long id;
	private String pergunta;
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

}
