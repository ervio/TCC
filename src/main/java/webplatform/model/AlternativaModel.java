package webplatform.model;

public class AlternativaModel {

	private long idAlternativa;
	private String resposta;
	private boolean correta;

	public long getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(long idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

}
