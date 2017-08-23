package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Aluno;

public interface AlunoDao {

	public Aluno findById(Long id);

	public Aluno saveOrUpdate(Aluno entity);

	public void delete(Aluno entity);

	public List<Aluno> findByEmailAndPassword(String email, String password);

	public List<Aluno> findByNameAndEmailWithoutTeacher(String name, String email);

	public List<Aluno> findByNameAndEmailAndTeacher(String name, String email, Long teacherId);
}
