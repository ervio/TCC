package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.GrammarQuestaoDao;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;

@Transactional
@Repository
public class GrammarQuestaoDaoImpl extends BaseDao<GrammarQuestao> implements GrammarQuestaoDao {

	public GrammarQuestaoDaoImpl() {
		super(GrammarQuestao.class);
	}

	@Override
	public GrammarQuestao saveOrUpdate(GrammarQuestao entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrammarQuestao> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.createAlias("definicaoResposta", "definicaoResposta");
		criteria.createAlias("definicaoResposta.exercicio", "exercicio");
		criteria.add(Restrictions.eq("exercicio.idExercicio", exerciseId));
		criteria.addOrder(Order.asc("sequencia"));
		return (List<GrammarQuestao>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(GrammarQuestao grammarQuestao) {
		hibernateTemplate.delete(grammarQuestao);
	}

	@Override
	public void deleteAll(List<GrammarQuestao> grammarQuestoes) {
		hibernateTemplate.deleteAll(grammarQuestoes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrammarQuestao> findByGrammarDefinicaoList(List<GrammarDefinicao> grammarDefinicaoList) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("definicaoResposta", grammarDefinicaoList));
		return (List<GrammarQuestao>) hibernateTemplate.findByCriteria(criteria);
	}

}
