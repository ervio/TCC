package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Alternativa;

public interface AlternativaDao {

	public Alternativa saveOrUpdate(Alternativa entity);

	public List<Alternativa> findByQuestao(Long questaoId);

	public void delete(Alternativa alternativa);
}
