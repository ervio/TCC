package webplatform.model;

import java.util.List;
import java.util.Set;

public class ExercicioModel {

	private long idExercicio;
	private long professorId;
	// TODO: remover atributo nome
	private String nome;
	private String nivel;
	private String valorNotaMaxima;
	private String writingQuestao;
	private MusicaModel musica;
	private List<QuestaoModel> questoes;
	private List<ExercicioAlunoModel> exercicioAlunos;
	private List<GrammarDefinicaoModel> grammarDefinicoes;
	private List<ReadingQuestaoModel> readingQuestoes;
	private Set<PronunciationQuestaoModel> pronunciationQuestoes;

	public long getIdExercicio() {
		return idExercicio;
	}

	public void setIdExercicio(long idExercicio) {
		this.idExercicio = idExercicio;
	}

	public long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getValorNotaMaxima() {
		return valorNotaMaxima;
	}

	public void setValorNotaMaxima(String valorNotaMaxima) {
		this.valorNotaMaxima = valorNotaMaxima;
	}

	public String getWritingQuestao() {
		return writingQuestao;
	}

	public void setWritingQuestao(String writingQuestao) {
		this.writingQuestao = writingQuestao;
	}

	public MusicaModel getMusica() {
		return musica;
	}

	public void setMusica(MusicaModel musica) {
		this.musica = musica;
	}

	public List<QuestaoModel> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoModel> questoes) {
		this.questoes = questoes;
	}

	public List<ExercicioAlunoModel> getExercicioAlunos() {
		return exercicioAlunos;
	}

	public void setExercicioAlunos(List<ExercicioAlunoModel> exercicioAlunos) {
		this.exercicioAlunos = exercicioAlunos;
	}

	public List<GrammarDefinicaoModel> getGrammarDefinicoes() {
		return grammarDefinicoes;
	}

	public void setGrammarDefinicoes(List<GrammarDefinicaoModel> grammarDefinicoes) {
		this.grammarDefinicoes = grammarDefinicoes;
	}

	public List<ReadingQuestaoModel> getReadingQuestoes() {
		return readingQuestoes;
	}

	public void setReadingQuestoes(List<ReadingQuestaoModel> readingQuestoes) {
		this.readingQuestoes = readingQuestoes;
	}

	public Set<PronunciationQuestaoModel> getPronunciationQuestoes() {
		return pronunciationQuestoes;
	}

	public void setPronunciationQuestoes(Set<PronunciationQuestaoModel> pronunciationQuestoes) {
		this.pronunciationQuestoes = pronunciationQuestoes;
	}

}
