package webplatform.model;

import java.util.Date;
import java.util.List;

public class ExercicioAlunoModel {

	private AlunoModel aluno;
	private long idExercicioAluno;
	private Date dataInicio;
	private Date dataFim;
	private int nota;
	private int chances;
	private List<QuestaoModel> questions;
	private long tempoResolucaoMillis;
	private String tempoResolucaoString;

	public AlunoModel getAluno() {
		return aluno;
	}

	public void setAluno(AlunoModel aluno) {
		this.aluno = aluno;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getChances() {
		return chances;
	}

	public void setChances(int chances) {
		this.chances = chances;
	}

	public long getIdExercicioAluno() {
		return idExercicioAluno;
	}

	public void setIdExercicioAluno(long idExercicioAluno) {
		this.idExercicioAluno = idExercicioAluno;
	}

	public List<QuestaoModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestaoModel> questions) {
		this.questions = questions;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public long getTempoResolucaoMillis() {
		return tempoResolucaoMillis;
	}

	public void setTempoResolucaoMillis(long tempoResolucaoMillis) {
		this.tempoResolucaoMillis = tempoResolucaoMillis;
	}

	public String getTempoResolucaoString() {
		return tempoResolucaoString;
	}

	public void setTempoResolucaoString(String tempoResolucaoString) {
		this.tempoResolucaoString = tempoResolucaoString;
	}

}