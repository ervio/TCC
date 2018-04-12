package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.PronunciationQuestaoDao;
import webplatform.model.entity.PronunciationQuestao;

@Transactional
@Repository
public class PronunciationQuestaoDaoImpl extends BaseDao<PronunciationQuestao> implements PronunciationQuestaoDao {

	public PronunciationQuestaoDaoImpl() {
		super(PronunciationQuestao.class);
	}

	@Override
	public PronunciationQuestao saveOrUpdate(PronunciationQuestao entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PronunciationQuestao> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.createAlias("exercicio", "exercicio");
		criteria.add(Restrictions.eq("exercicio.idExercicio", exerciseId));
		return (List<PronunciationQuestao>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(PronunciationQuestao pronunciationQuestao) {
		hibernateTemplate.delete(pronunciationQuestao);
	}
}
