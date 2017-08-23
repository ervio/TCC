package webplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webplatform.dao.ExercicioAlunoDao;
import webplatform.model.entity.ExercicioAluno;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class PerformanceController {

	@Autowired
	private ExercicioAlunoDao exercicioAlunoDao;

	/**
	 * If the logged user is a student, all the exercises resolved by him will
	 * be searched otherwise all the exercises resolved by the students
	 * associated to the logged teacher will be searched
	 * 
	 * @param studentName
	 * @param studentEmail
	 * @param exerciseName
	 * @param level
	 * @param studentId
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/searchResolvedExercises/{studentName}/{studentEmail}/{exerciseName}/{level}/{studentId}/{teacherId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchResolvedExercises(@PathVariable("studentName") String studentName,
			@PathVariable("studentEmail") String studentEmail, @PathVariable("exerciseName") String exerciseName,
			@PathVariable("level") String level, @PathVariable("studentId") String studentId,
			@PathVariable("teacherId") String teacherId) {
		List<ExercicioAluno> exercises = exercicioAlunoDao.findResolved(studentName, studentEmail, exerciseName, level,
				studentId, teacherId);
		return new ResponseEntity(exercises, HttpStatus.OK);
	}

}
