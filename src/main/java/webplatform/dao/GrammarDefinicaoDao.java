package webplatform.dao;

import java.util.List;

import webplatform.model.entity.GrammarDefinicao;

public interface GrammarDefinicaoDao {

	public GrammarDefinicao saveOrUpdate(GrammarDefinicao entity);

	public List<GrammarDefinicao> findByExercise(Long exerciseId);
}
