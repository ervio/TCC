package webplatform.dao;

import java.util.List;

import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;
import webplatform.model.entity.ReadingResposta;

public interface ReadingRespostaDao {

	public ReadingResposta saveOrUpdate(ReadingResposta entity);

	public List<ReadingResposta> findByReadingAlternativas(List<ReadingAlternativa> readingAlternativas);

	public List<ReadingResposta> findByReadingQuestoes(List<ReadingQuestao> readingQuestoes);

	public List<ReadingResposta> findByExercicioAlunoList(List<ExercicioAluno> exercicioAlunoList);

	public void deleteAll(List<ReadingResposta> readingRespostas);
}
