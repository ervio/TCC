package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Professor;

public interface ProfessorDao {

	public Professor findById(Long id);

	public Professor saveOrUpdate(Professor entity);

	public void delete(Professor entity);

	public List<Professor> findByEmailAndPassword(String email, String password);
}
