package webplatform.dao;

import java.util.List;

import webplatform.model.entity.ExercicioAluno;

public interface ExercicioAlunoDao {

	public void saveOrUpdate(ExercicioAluno exercicioAluno);

	public List<ExercicioAluno> findNotResolvedByStudentId(Long studentId);

	public List<ExercicioAluno> findNotResolvedByExerciseId(Long exerciseId);

	public List<ExercicioAluno> findResolved(String studentName, String studentEmail, String exerciseName, String level,
			String studentId, String teacherId);

	public List<ExercicioAluno> findResolvedByExerciseId(Long exerciseId);

	public ExercicioAluno findById(Long exercicioAlunoId);

	public List<ExercicioAluno> findByExerciseId(Long exerciseId);

	public void deleteAll(List<ExercicioAluno> exercicioAlunoList);
}
