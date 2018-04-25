package webplatform.dao.impl; 

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ForumPostDao;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ForumPost;

@Transactional
@Repository
public class ForumPostDaoImpl extends BaseDao<ForumPost> implements ForumPostDao {

	public ForumPostDaoImpl() {
		super(ForumPost.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> findByExercise(Long exerciseId) {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("exercicio", new Exercicio(exerciseId)));
		criteria.addOrder(Order.asc("dataPostagem"));
		return (List<ForumPost>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public ForumPost saveOrUpdate(ForumPost entity) {
		if (entity.getId() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
