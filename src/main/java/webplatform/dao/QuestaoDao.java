package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Questao;

public interface QuestaoDao {

	public Questao saveOrUpdate(Questao entity);

	public List<Questao> findByExercise(Long exerciseId);

	public void delete(Questao questao);
}
