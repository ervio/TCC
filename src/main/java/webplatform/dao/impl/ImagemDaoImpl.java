package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import webplatform.dao.ImagemDao;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.Imagem;

@Transactional
@Repository
public class ImagemDaoImpl extends BaseDao<Imagem> implements ImagemDao {

	public ImagemDaoImpl() {
		super(Imagem.class);
	}

	@Override
	public Imagem saveOrUpdate(Imagem entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Imagem> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		criteria.addOrder(Order.asc("id"));
		return (List<Imagem>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(Imagem picture) {
		hibernateTemplate.delete(picture);
	}

	@Override
	public void deleteAll(List<Imagem> pictures) {
		if (!CollectionUtils.isEmpty(pictures)) {
			hibernateTemplate.deleteAll(pictures);
		}
	}

	@Override
	public Imagem findById(Long id) {
		return this.hibernateTemplate.get(Imagem.class, id);
	}
}
