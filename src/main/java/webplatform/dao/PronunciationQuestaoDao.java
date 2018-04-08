package webplatform.dao;

import java.util.List;

import webplatform.model.entity.PronunciationQuestao;

public interface PronunciationQuestaoDao {

	public PronunciationQuestao saveOrUpdate(PronunciationQuestao entity);

	public List<PronunciationQuestao> findByExercise(Long exerciseId);
}
