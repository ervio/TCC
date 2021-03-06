package webplatform.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	private String letraOrdenar;
	private String link;

	public Musica() {
	}

	public Musica(Long idMusica) {
		this.idMusica = idMusica;
	}

	public Musica(Long idMusica, String nome, String cantor, String letra, String letraOrdenar, String link) {
		this.idMusica = idMusica;
		this.nome = nome;
		this.cantor = cantor;
		this.letra = letra;
		this.letraOrdenar = letraOrdenar;
		this.link = link;
	}

	@Id
	@GenericGenerator(name = "MUSICA_SEQUENCE", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "MUSICA_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "MUSICA_SEQUENCE")
	@Column(name = "ID_MUSICA", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getIdMusica() {
		return this.idMusica;
	}

	public void setIdMusica(Long idMusica) {
		this.idMusica = idMusica;
	}

	@Column(name = "NOME", length = 100)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "CANTOR", length = 100)
	public String getCantor() {
		return this.cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	// @Lob
	@Column(name = "LETRA", columnDefinition = "text")
	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	// @Lob
	@Column(name = "LETRA_ORDENAR", columnDefinition = "text")
	public String getLetraOrdenar() {
		return letraOrdenar;
	}

	public void setLetraOrdenar(String letraOrdenar) {
		this.letraOrdenar = letraOrdenar;
	}

	@Column(name = "LINK", length = 100)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
