package webplatform.dao;

import java.util.List;

import webplatform.model.entity.GrammarQuestao;

public interface GrammarQuestaoDao {

	public GrammarQuestao saveOrUpdate(GrammarQuestao entity);

	public List<GrammarQuestao> findByExercise(Long exerciseId);

	public void delete(GrammarQuestao grammarQuestao);
}
