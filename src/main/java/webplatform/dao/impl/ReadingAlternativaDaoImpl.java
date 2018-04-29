package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingAlternativaDao;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;

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
		criteria.addOrder(Order.asc("sequencia"));
		return (List<ReadingAlternativa>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingAlternativa> findByQuestion(Long questaoId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("questao", new ReadingQuestao(questaoId)));
		criteria.addOrder(Order.asc("sequencia"));
		return (List<ReadingAlternativa>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(ReadingAlternativa readingAlternativa) {
		hibernateTemplate.delete(readingAlternativa);
	}

	@Override
	public void deleteAll(List<ReadingAlternativa> readingAlternativas) {
		hibernateTemplate.deleteAll(readingAlternativas);
	}
}
