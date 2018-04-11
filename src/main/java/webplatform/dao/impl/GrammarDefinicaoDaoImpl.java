package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.GrammarDefinicaoDao;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.GrammarDefinicao;

@Transactional
@Repository
public class GrammarDefinicaoDaoImpl extends BaseDao<GrammarDefinicao> implements GrammarDefinicaoDao {

	public GrammarDefinicaoDaoImpl() {
		super(GrammarDefinicao.class);
	}

	@Override
	public GrammarDefinicao saveOrUpdate(GrammarDefinicao entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrammarDefinicao> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		criteria.addOrder(Order.asc("id"));
		return (List<GrammarDefinicao>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(GrammarDefinicao grammarDefinicao) {
		hibernateTemplate.delete(grammarDefinicao);
	}
}
