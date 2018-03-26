package webplatform.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webplatform.dao.ImagemDao;
import webplatform.model.entity.Imagem;

@Transactional
@Repository
public class ImagemDaoImpl extends BaseDao<Imagem> implements ImagemDao {

	public ImagemDaoImpl() {
		super(Imagem.class);
	}

	@Override
	public Imagem saveOrUpdate(Imagem entity) {
		if (entity.getIdImagem() != null) {
			hibernateTemplate.update(entity);
		} else {
			hibernateTemplate.save(entity);
		}
		return entity;
	}

}
