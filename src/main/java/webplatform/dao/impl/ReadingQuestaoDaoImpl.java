package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingQuestaoDao;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ReadingQuestao;

@Transactional
@Repository
public class ReadingQuestaoDaoImpl extends BaseDao<ReadingQuestao> implements ReadingQuestaoDao {

	public ReadingQuestaoDaoImpl() {
		super(ReadingQuestao.class);
	}

	@Override
	public ReadingQuestao saveOrUpdate(ReadingQuestao entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingQuestao> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		criteria.addOrder(Order.asc("sequencia"));
		return (List<ReadingQuestao>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(ReadingQuestao readingQuestao) {
		hibernateTemplate.delete(readingQuestao);
	}
}
