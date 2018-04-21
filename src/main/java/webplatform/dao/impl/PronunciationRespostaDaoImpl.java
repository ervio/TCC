package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.PronunciationRespostaDao;
import webplatform.model.entity.PronunciationResposta;

@Transactional
@Repository
public class PronunciationRespostaDaoImpl extends BaseDao<PronunciationResposta> implements PronunciationRespostaDao {

	public PronunciationRespostaDaoImpl() {
		super(PronunciationResposta.class);
	}

	@Override
	public PronunciationResposta saveOrUpdate(PronunciationResposta entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
