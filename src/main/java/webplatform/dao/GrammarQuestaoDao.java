package webplatform.dao;

import java.util.List;

import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;

public interface GrammarQuestaoDao {

	public GrammarQuestao saveOrUpdate(GrammarQuestao entity);

	public List<GrammarQuestao> findByExercise(Long exerciseId);

	public List<GrammarQuestao> findByGrammarDefinicaoList(List<GrammarDefinicao> grammarDefinicaoList);

	public void delete(GrammarQuestao grammarQuestao);

	public void deleteAll(List<GrammarQuestao> grammarQuestoes);
}
