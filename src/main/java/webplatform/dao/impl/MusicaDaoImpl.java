package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.MusicaDao;
import webplatform.model.entity.Musica;

@Transactional
@Repository
public class MusicaDaoImpl implements MusicaDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Musica findById(Long id) {
		return hibernateTemplate.get(Musica.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Musica> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Musica.class);
		return (List<Musica>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public Musica saveOrUpdate(Musica entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void delete(Musica entity) {
		hibernateTemplate.delete(entity);
	}
}
