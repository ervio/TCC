package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingQuestaoDao;
import webplatform.model.entity.ReadingQuestao;

@Transactional
@Repository
public class ReadingQuestaoDaoImpl extends BaseDao<ReadingQuestao> implements ReadingQuestaoDao {

	public ReadingQuestaoDaoImpl() {
		super(ReadingQuestao.class);
	}

	@Override
	public ReadingQuestao saveOrUpdate(ReadingQuestao entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
