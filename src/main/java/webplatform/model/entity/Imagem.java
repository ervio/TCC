package webplatform.model.entity;

// default package
// Generated Mar 25, 2018 11:25:23 PM by Hibernate Tools 5.1.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Imagem generated by hbm2java
 */
@Entity
@Table(name = "IMAGEM")
public class Imagem implements java.io.Serializable {

	private Long id;
	private Exercicio exercicio;
	private byte[] bytes;
	private String nome;

	public Imagem() {
	}

	public Imagem(Long idImagem) {
		this.id = idImagem;
	}

	public Imagem(Long id, byte[] bytes, String nome, Exercicio exercicio) {
		this.id = id;
		this.bytes = bytes;
		this.nome = nome;
		this.exercicio = exercicio;
	}

	@Id
	@SequenceGenerator(name = "IMAGEM_SEQUENCE", sequenceName = "IMAGEM_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "IMAGEM_SEQUENCE")
	@Column(name = "ID_IMAGEM", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Lob
	@Column(name = "BYTES")
	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Column(name = "NOME", length = 50)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EXERCICIO")
	public Exercicio getExercicio() {
		return this.exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
}
