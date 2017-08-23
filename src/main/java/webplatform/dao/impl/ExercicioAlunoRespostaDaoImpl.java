package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ExercicioAlunoRespostaDao;
import webplatform.model.entity.ExercicioAlunoResposta;

@Transactional
@Repository
public class ExercicioAlunoRespostaDaoImpl extends BaseDao<ExercicioAlunoResposta>
		implements ExercicioAlunoRespostaDao {

	public ExercicioAlunoRespostaDaoImpl() {
		super(ExercicioAlunoResposta.class);
	}

	@Override
	public ExercicioAlunoResposta saveOrUpdate(ExercicioAlunoResposta entity) {
		if (entity.getIdExercicioAlunoResposta() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
