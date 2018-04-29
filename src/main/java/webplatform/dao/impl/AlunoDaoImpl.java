package webplatform.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.AlunoDao;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Professor;

@Transactional
@Repository
public class AlunoDaoImpl extends BaseDao<Aluno> implements AlunoDao {

	public AlunoDaoImpl() {
		super(Aluno.class);
	}

	@Override
	public Aluno findById(Long id) {
		return this.hibernateTemplate.get(Aluno.class, id);
	}

	@Override
	public Aluno saveOrUpdate(Aluno entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void delete(Aluno entity) {
		this.hibernateTemplate.delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByEmailAndPassword(String email, String password) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		return (List<Aluno>) this.hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByNameAndEmailWithoutTeacher(String name, String email) {
		DetachedCriteria criteria = getDetachedCriteria();
		if (StringUtils.stripToNull(name) != null) {
			criteria.add(Restrictions.ilike("nome", name, MatchMode.ANYWHERE));
		}
		if (StringUtils.stripToNull(email) != null) {
			criteria.add(Restrictions.eq("email", email));
		}
		criteria.add(Restrictions.isNull("professor"));
		criteria.addOrder(Order.asc("nome"));
		return (List<Aluno>) this.hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByNameAndEmailAndTeacher(String name, String email, Long teacherId) {
		DetachedCriteria criteria = getDetachedCriteria();
		if (StringUtils.stripToNull(name) != null) {
			criteria.add(Restrictions.ilike("nome", name, MatchMode.ANYWHERE));
		}
		if (StringUtils.stripToNull(email) != null) {
			criteria.add(Restrictions.eq("email", email));
		}
		criteria.add(Restrictions.eq("professor", new Professor(teacherId)));
		criteria.addOrder(Order.asc("nome"));
		return (List<Aluno>) this.hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByEmail(String email) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("email", email));
		return (List<Aluno>) this.hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findDifferentWithSameEmail(String email, Long studentId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.ne("id", studentId));
		return (List<Aluno>) this.hibernateTemplate.findByCriteria(criteria);
	}

}
