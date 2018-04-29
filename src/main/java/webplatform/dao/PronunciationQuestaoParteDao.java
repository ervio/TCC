package webplatform.dao;

import java.util.List;

import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.PronunciationQuestaoParte;

public interface PronunciationQuestaoParteDao {

	public PronunciationQuestaoParte saveOrUpdate(PronunciationQuestaoParte entity);

	public List<PronunciationQuestaoParte> findByExercise(Long exerciseId);

	public List<PronunciationQuestaoParte> findByQuestion(Long questaoId);

	public List<PronunciationQuestaoParte> findByPronunciationQuestaoList(
			List<PronunciationQuestao> pronunciationQuestoes);

	public void delete(PronunciationQuestaoParte pronunciationQuestaoParte);

	public void deleteAll(List<PronunciationQuestaoParte> pronunciationQuestaoPartes);
}
