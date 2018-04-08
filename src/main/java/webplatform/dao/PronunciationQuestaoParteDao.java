package webplatform.dao;

import java.util.List;

import webplatform.model.entity.PronunciationQuestaoParte;

public interface PronunciationQuestaoParteDao {

	public PronunciationQuestaoParte saveOrUpdate(PronunciationQuestaoParte entity);

	public List<PronunciationQuestaoParte> findByExercise(Long exerciseId);
}
