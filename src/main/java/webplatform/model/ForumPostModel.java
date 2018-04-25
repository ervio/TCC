package webplatform.model; 

import java.util.Date;

public class ForumPostModel {

	private Long id;
	private String texto;
	private Date dataPostagem;
	private ExercicioModel exercicio;
	private ForumPostModel postRespondido;
	private AlunoModel aluno;
	private ProfessorModel professor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public ExercicioModel getExercicio() {
		return exercicio;
	}

	public void setExercicio(ExercicioModel exercicio) {
		this.exercicio = exercicio;
	}

	public ForumPostModel getPostRespondido() {
		return postRespondido;
	}

	public void setPostRespondido(ForumPostModel postRespondido) {
		this.postRespondido = postRespondido;
	}

	public AlunoModel getAluno() {
		return aluno;
	}

	public void setAluno(AlunoModel aluno) {
		this.aluno = aluno;
	}

	public ProfessorModel getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorModel professor) {
		this.professor = professor;
	}

}
