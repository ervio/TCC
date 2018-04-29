package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.VocabularyRespostaDao;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.VocabularyResposta;

@Transactional
@Repository
public class VocabularyRespostaDaoImpl extends BaseDao<VocabularyResposta> implements VocabularyRespostaDao {

	public VocabularyRespostaDaoImpl() {
		super(VocabularyResposta.class);
	}

	@Override
	public VocabularyResposta saveOrUpdate(VocabularyResposta entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VocabularyResposta> findByImageList(List<Imagem> imagens) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.in("imagem", imagens));
		return (List<VocabularyResposta>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void deleteAll(List<VocabularyResposta> vocabularyRespostas) {
		hibernateTemplate.deleteAll(vocabularyRespostas);
	}

}
