package webplatform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.PaisDao;
import webplatform.model.entity.Pais;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class PaisDaoImpl extends BaseDao<Pais> implements PaisDao {

	public PaisDaoImpl() {
		super(Pais.class);
	}

	@Override
	public List<Pais> listAll() {
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.addOrder(Order.asc("nome"));
		return (List<Pais>) hibernateTemplate.findByCriteria(criteria);
	}

}
