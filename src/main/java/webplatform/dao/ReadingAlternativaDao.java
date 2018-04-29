package webplatform.dao;

import java.util.List;

import webplatform.model.entity.ReadingAlternativa;

public interface ReadingAlternativaDao {

	public ReadingAlternativa saveOrUpdate(ReadingAlternativa entity);

	public List<ReadingAlternativa> findByExercise(Long exerciseId);

	public List<ReadingAlternativa> findByQuestion(Long questaoId);

	public void delete(ReadingAlternativa readingAlternativa);

	public void deleteAll(List<ReadingAlternativa> readingAlternativas);
}
