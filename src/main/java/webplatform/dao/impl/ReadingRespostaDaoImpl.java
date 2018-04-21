package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingRespostaDao;
import webplatform.model.entity.ReadingResposta;

@Transactional
@Repository
public class ReadingRespostaDaoImpl extends BaseDao<ReadingResposta> implements ReadingRespostaDao {

	public ReadingRespostaDaoImpl() {
		super(ReadingResposta.class);
	}

	@Override
	public ReadingResposta saveOrUpdate(ReadingResposta entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
