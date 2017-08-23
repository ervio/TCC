package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ConviteDao;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Convite;
import webplatform.model.entity.Professor;

@Transactional
@Repository
public class ConviteDaoImpl extends BaseDao<Convite> implements ConviteDao {

	public ConviteDaoImpl() {
		super(Convite.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convite> findByTeacherAndStudent(Long teacherId, Long studentId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("professor", new Professor(teacherId)));
		criteria.add(Restrictions.eq("aluno", new Aluno(studentId)));
		return (List<Convite>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(Convite invitation) {
		hibernateTemplate.delete(invitation);
	}

	@Override
	public void saveOrUpdate(Convite convite) {
		hibernateTemplate.saveOrUpdate(convite);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convite> findByStudent(Long studentId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("aluno", new Aluno(studentId)));
		return (List<Convite>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void deleteAll(List<Convite> invitations) {
		hibernateTemplate.deleteAll(invitations);
	}

}
