package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.PronunciationQuestaoParteDao;
import webplatform.model.entity.PronunciationQuestaoParte;

@Transactional
@Repository
public class PronunciationQuestaoParteDaoImpl extends BaseDao<PronunciationQuestaoParte>
		implements PronunciationQuestaoParteDao {

	public PronunciationQuestaoParteDaoImpl() {
		super(PronunciationQuestaoParte.class);
	}

	@Override
	public PronunciationQuestaoParte saveOrUpdate(PronunciationQuestaoParte entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PronunciationQuestaoParte> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.createAlias("pronunciationQuestao", "pronunciationQuestao");
		criteria.createAlias("pronunciationQuestao.exercicio", "exercicio");
		criteria.add(Restrictions.eq("exercicio.idExercicio", exerciseId));
		criteria.addOrder(Order.asc("sequencia"));
		return (List<PronunciationQuestaoParte>) hibernateTemplate.findByCriteria(criteria);
	}

}
