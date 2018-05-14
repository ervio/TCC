package webplatform.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ExercicioAlunoDao;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ExercicioAluno;

@Transactional
@Repository
public class ExercicioAlunoDaoImpl extends BaseDao<ExercicioAluno> implements ExercicioAlunoDao {

	public ExercicioAlunoDaoImpl() {
		super(ExercicioAluno.class);
	}

	@Override
	public void saveOrUpdate(ExercicioAluno exercicioAluno) {
		hibernateTemplate.saveOrUpdate(exercicioAluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExercicioAluno> findNotResolvedByStudentId(Long studentId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("aluno", new Aluno(studentId)));
		criteria.add(Restrictions.isNull("totalQuestoes"));
		criteria.add(Restrictions.isNull("questoesCorretas"));
		return (List<ExercicioAluno>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExercicioAluno> findNotResolvedByExerciseId(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		criteria.add(Restrictions.isNull("totalQuestoes"));
		criteria.add(Restrictions.isNull("questoesCorretas"));
		return (List<ExercicioAluno>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public ExercicioAluno findById(Long exercicioAlunoId) {
		return this.hibernateTemplate.get(ExercicioAluno.class, exercicioAlunoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExercicioAluno> findResolved(String studentName, String studentEmail, String songName, String level,
			String studentId, String teacherId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.createAlias("aluno", "aluno");
		criteria.createAlias("exercicio", "exercicio");
		criteria.createAlias("exercicio.musica", "musica");

		if (StringUtils.stripToNull(studentName) != null) {
			criteria.add(Restrictions.like("aluno.nome", studentName, MatchMode.ANYWHERE));
		}
		if (StringUtils.stripToNull(studentEmail) != null) {
			criteria.add(Restrictions.eq("aluno.email", studentEmail));
		}
		if (StringUtils.stripToNull(songName) != null) {
			criteria.add(Restrictions.like("musica.nome", songName, MatchMode.ANYWHERE));
		}
		if (StringUtils.stripToNull(level) != null) {
			criteria.add(Restrictions.eq("exercicio.nivel", level));
		}
		if (StringUtils.stripToNull(studentId) != null) {
			criteria.add(Restrictions.eq("aluno.id", Long.parseLong(studentId)));
		}
		if (StringUtils.stripToNull(teacherId) != null) {
			criteria.createAlias("exercicio.professor", "professor");
			criteria.add(Restrictions.eq("professor.id", Long.parseLong(teacherId)));
		}
		criteria.add(Restrictions.isNotNull("totalQuestoes"));
		criteria.add(Restrictions.isNotNull("questoesCorretas"));
		criteria.addOrder(Order.asc("musica.nome"));
		return (List<ExercicioAluno>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExercicioAluno> findResolvedByExerciseId(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		criteria.add(Restrictions.isNotNull("nota"));
		return (List<ExercicioAluno>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExercicioAluno> findByExerciseId(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		return (List<ExercicioAluno>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void deleteAll(List<ExercicioAluno> exercicioAlunoList) {
		hibernateTemplate.deleteAll(exercicioAlunoList);
	}

}
