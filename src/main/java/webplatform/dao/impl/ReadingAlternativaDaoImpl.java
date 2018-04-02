package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingAlternativaDao;
import webplatform.model.entity.ReadingAlternativa;

@Transactional
@Repository
public class ReadingAlternativaDaoImpl extends BaseDao<ReadingAlternativa> implements ReadingAlternativaDao {

	public ReadingAlternativaDaoImpl() {
		super(ReadingAlternativa.class);
	}

	@Override
	public ReadingAlternativa saveOrUpdate(ReadingAlternativa entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
