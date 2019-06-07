package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ReadingRespostaDao;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingResposta> findByReadingAlternativas(List<ReadingAlternativa> readingAlternativas) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("readingQuestaoResposta", readingAlternativas));
		return (List<ReadingResposta>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void deleteAll(List<ReadingResposta> readingRespostas) {
		hibernateTemplate.deleteAll(readingRespostas);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingResposta> findByReadingQuestoes(List<ReadingQuestao> readingQuestoes) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("readingQuestao", readingQuestoes));
		return (List<ReadingResposta>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingResposta> findByExercicioAlunoList(List<ExercicioAluno> exercicioAlunoList) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("exercicioAluno", exercicioAlunoList));
		return (List<ReadingResposta>) hibernateTemplate.findByCriteria(criteria);
	}

}
