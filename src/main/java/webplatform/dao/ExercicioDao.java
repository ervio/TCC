package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Exercicio;

public interface ExercicioDao {

	public Exercicio saveOrUpdate(Exercicio entity);

	public List<Exercicio> findByTeacher(Long teacherId);

	public void delete(Exercicio exercicio);

	public List<Exercicio> findResolvedExercises();

	public List<Exercicio> findNotAssignedExercises(Long studentId);

	public List<Exercicio> listAll();
}
