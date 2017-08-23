package webplatform.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Musica generated by hbm2java
 */
@Entity
@Table(name = "MUSICA")
public class Musica implements java.io.Serializable {

	private Long idMusica;
	private String nome;
	private String cantor;
	private String letra;
	private String link;

	public Musica() {
	}

	public Musica(Long idMusica) {
		this.idMusica = idMusica;
	}

	public Musica(Long idMusica, String nome, String cantor, String letra, String link) {
		this.idMusica = idMusica;
		this.nome = nome;
		this.cantor = cantor;
		this.letra = letra;
		this.link = link;
	}

	@Id
	@SequenceGenerator(name = "MUSICA_SEQUENCE", sequenceName = "MUSICA_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MUSICA_SEQUENCE")
	@Column(name = "ID_MUSICA", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getIdMusica() {
		return this.idMusica;
	}

	public void setIdMusica(Long idMusica) {
		this.idMusica = idMusica;
	}

	@Column(name = "NOME", length = 30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "CANTOR", length = 20)
	public String getCantor() {
		return this.cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	@Lob
	@Column(name = "LETRA")
	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	@Column(name = "LINK", length = 50)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
