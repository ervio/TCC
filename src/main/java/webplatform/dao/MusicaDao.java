package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Musica;

public interface MusicaDao {

	public Musica findById(Long id);

	public List<Musica> findAll();

	public Musica saveOrUpdate(Musica entity);

	public void delete(Musica entity);
}
