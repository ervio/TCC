package webplatform.dao;

import java.util.List;

import webplatform.model.entity.ReadingQuestao;

public interface ReadingQuestaoDao {

	public ReadingQuestao saveOrUpdate(ReadingQuestao entity);

	public List<ReadingQuestao> findByExercise(Long exerciseId);

	public void delete(ReadingQuestao readingQuestao);
}
