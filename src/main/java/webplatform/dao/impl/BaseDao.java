package webplatform.dao.impl;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class BaseDao<T extends Serializable> {

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	protected Class<T> persistentClass;

	public BaseDao(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public DetachedCriteria getDetachedCriteria() {
		return DetachedCriteria.forClass(persistentClass);
	}

}
