package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.GrammarRespostaDao;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.GrammarResposta;

@Transactional
@Repository
public class GrammarRespostaDaoImpl extends BaseDao<GrammarResposta> implements GrammarRespostaDao {

	public GrammarRespostaDaoImpl() {
		super(GrammarResposta.class);
	}

	@Override
	public GrammarResposta saveOrUpdate(GrammarResposta entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrammarResposta> findByGrammarQuestaoList(List<GrammarQuestao> grammarQuestoes) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("grammarQuestao", grammarQuestoes));
		return (List<GrammarResposta>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void deleteAll(List<GrammarResposta> grammarRespostas) {
		hibernateTemplate.deleteAll(grammarRespostas);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrammarResposta> findByGrammarDefinicaoList(List<GrammarDefinicao> grammarDefinicoes) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("grammarQuestaoResposta", grammarDefinicoes));
		return (List<GrammarResposta>) hibernateTemplate.findByCriteria(criteria);
	}

}
