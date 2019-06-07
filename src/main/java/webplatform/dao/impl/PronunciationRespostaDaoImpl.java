package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.PronunciationRespostaDao;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.PronunciationQuestaoParte;
import webplatform.model.entity.PronunciationResposta;

@Transactional
@Repository
public class PronunciationRespostaDaoImpl extends BaseDao<PronunciationResposta> implements PronunciationRespostaDao {

	public PronunciationRespostaDaoImpl() {
		super(PronunciationResposta.class);
	}

	@Override
	public PronunciationResposta saveOrUpdate(PronunciationResposta entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PronunciationResposta> findByPronunciationQuestaoParteList(
			List<PronunciationQuestaoParte> pronunciationQuestaoPartes) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("pronunciationQuestaoParte", pronunciationQuestaoPartes));
		return (List<PronunciationResposta>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void deleteAll(List<PronunciationResposta> pronunciationRespostas) {
		hibernateTemplate.deleteAll(pronunciationRespostas);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PronunciationResposta> findByExercicioAlunoList(List<ExercicioAluno> exercicioAlunoList) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("exercicioAluno", exercicioAlunoList));
		return (List<PronunciationResposta>) hibernateTemplate.findByCriteria(criteria);
	}

}
