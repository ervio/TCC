package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Convite;

public interface ConviteDao {

	public List<Convite> findByTeacherAndStudent(Long teacherId, Long studentId);

	public List<Convite> findByStudent(Long studentId);

	public void delete(Convite invitation);

	public void deleteAll(List<Convite> invitations);

	public void saveOrUpdate(Convite convite);
}
