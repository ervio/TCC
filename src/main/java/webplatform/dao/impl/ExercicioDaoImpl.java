package webplatform.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ExercicioDao;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.Professor;

@Transactional
@Repository
public class ExercicioDaoImpl extends BaseDao<Exercicio> implements ExercicioDao {

	public ExercicioDaoImpl() {
		super(Exercicio.class);
	}

	@Override
	public Exercicio saveOrUpdate(Exercicio entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercicio> findByTeacher(Long teacherId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("professor", new Professor(teacherId)));
		return (List<Exercicio>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void delete(Exercicio exercicio) {
		hibernateTemplate.delete(exercicio);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercicio> findResolvedExercises() {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.isNotEmpty("exercicioAlunos"));
		criteria.createAlias("exercicioAlunos", "exercicioAlunos");
		criteria.add(Restrictions.isNotNull("exercicioAlunos.questoesCorretas"));
		criteria.add(Restrictions.isNotNull("exercicioAlunos.totalQuestoes"));
		criteria.addOrder(Order.asc("idExercicio"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Exercicio>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercicio> listAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Exercicio.class);
		return (List<Exercicio>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercicio> findNotAssignedExercises(Long studentId) {
		DetachedCriteria subqueryExercicioAluno = DetachedCriteria.forClass(ExercicioAluno.class);
		subqueryExercicioAluno.createAlias("exercicio", "exercicio");
		subqueryExercicioAluno.add(Restrictions.eq("aluno", new Aluno(studentId)));
		subqueryExercicioAluno.add(Restrictions.isNull("totalQuestoes"));
		subqueryExercicioAluno.add(Restrictions.isNull("questoesCorretas"));
		subqueryExercicioAluno.setProjection(Projections.property("exercicio.idExercicio"));

		DetachedCriteria criteria = DetachedCriteria.forClass(Exercicio.class);
		criteria.add(Property.forName("idExercicio").notIn(subqueryExercicioAluno));

		return (List<Exercicio>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public Exercicio findById(Long id) {
		return this.hibernateTemplate.get(Exercicio.class, id);
	}
}
