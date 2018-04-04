package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingAlternativaDao;
import webplatform.model.entity.ReadingAlternativa;

@Transactional
@Repository
public class ReadingAlternativaDaoImpl extends BaseDao<ReadingAlternativa> implements ReadingAlternativaDao {

	public ReadingAlternativaDaoImpl() {
		super(ReadingAlternativa.class);
	}

	@Override
	public ReadingAlternativa saveOrUpdate(ReadingAlternativa entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingAlternativa> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.createAlias("questao", "questao");
		criteria.createAlias("questao.exercicio", "exercicio");
		criteria.add(Restrictions.eq("exercicio.idExercicio", exerciseId));
		return (List<ReadingAlternativa>) hibernateTemplate.findByCriteria(criteria);
	}

}
