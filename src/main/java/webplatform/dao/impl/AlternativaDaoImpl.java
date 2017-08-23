package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.AlternativaDao;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.Questao;

@Transactional
@Repository
public class AlternativaDaoImpl extends BaseDao<Alternativa> implements AlternativaDao {

	public AlternativaDaoImpl() {
		super(Alternativa.class);
	}

	@Override
	public Alternativa saveOrUpdate(Alternativa entity) {
		if (entity.getIdAlternativa() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alternativa> findByQuestao(Long questaoId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("questao", new Questao(questaoId)));
		return (List<Alternativa>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(Alternativa alternativa) {
		hibernateTemplate.delete(alternativa);
	}
}
