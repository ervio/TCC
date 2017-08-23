package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.QuestaoDao;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.Questao;

@Transactional
@Repository
public class QuestaoDaoImpl extends BaseDao<Questao> implements QuestaoDao {

	public QuestaoDaoImpl() {
		super(Questao.class);
	}

	@Override
	public Questao saveOrUpdate(Questao entity) {
		if (entity.getIdQuestao() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questao> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		return (List<Questao>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(Questao questao) {
		hibernateTemplate.delete(questao);
	}
}
