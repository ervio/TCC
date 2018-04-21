package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.GrammarRespostaDao;
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

}
