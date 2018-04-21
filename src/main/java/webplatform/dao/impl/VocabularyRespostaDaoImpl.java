package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.VocabularyRespostaDao;
import webplatform.model.entity.VocabularyResposta;

@Transactional
@Repository
public class VocabularyRespostaDaoImpl extends BaseDao<VocabularyResposta> implements VocabularyRespostaDao {

	public VocabularyRespostaDaoImpl() {
		super(VocabularyResposta.class);
	}

	@Override
	public VocabularyResposta saveOrUpdate(VocabularyResposta entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
