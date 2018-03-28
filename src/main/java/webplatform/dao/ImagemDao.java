package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Imagem;

public interface ImagemDao {

	public Imagem saveOrUpdate(Imagem entity);

	public List<Imagem> findByExercise(Long exerciseId);

	public void delete(Imagem imagem);

	public void deleteAll(List<Imagem> pictures);
}
