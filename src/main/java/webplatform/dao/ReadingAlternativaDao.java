package webplatform.dao;

import java.util.List;

import webplatform.model.entity.ReadingAlternativa;

public interface ReadingAlternativaDao {

	public ReadingAlternativa saveOrUpdate(ReadingAlternativa entity);

	public List<ReadingAlternativa> findByExercise(Long exerciseId);

	public void delete(ReadingAlternativa readingAlternativa);
}
