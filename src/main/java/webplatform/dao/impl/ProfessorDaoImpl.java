package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ProfessorDao;
import webplatform.model.entity.Professor;

@Transactional
@Repository
public class ProfessorDaoImpl extends BaseDao<Professor> implements ProfessorDao {

	public ProfessorDaoImpl() {
		super(Professor.class);
	}

	@Override
	public Professor findById(Long id) {
		return this.hibernateTemplate.get(Professor.class, id);
	}

	@Override
	public Professor saveOrUpdate(Professor entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void delete(Professor entity) {
		this.hibernateTemplate.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Professor> findByEmailAndPassword(String email, String password) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eqOrIsNull("email", email));
		criteria.add(Restrictions.eqOrIsNull("password", password));
		return (List<Professor>) hibernateTemplate.findByCriteria(criteria);
	}

}
