package webplatform.model;

public class MusicaModel {

	private long idMusica;
	private String nome;
	private String cantor;
	private String letra;
	private String link;

	public long getIdMusica() {
		return idMusica;
	}

	public void setIdMusica(long idMusica) {
		this.idMusica = idMusica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCantor() {
		return cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
