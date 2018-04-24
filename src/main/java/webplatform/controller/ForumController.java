package webplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webplatform.dao.ExercicioDao;
import webplatform.model.entity.Exercicio;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class ForumController {

	@Autowired
	private ExercicioDao exercicioDao;

	/**
	 * Search all the created exercises
	 * 
	 * @param questionId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/getAllExercises", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCountries() {
		List<Exercicio> exercicios = exercicioDao.listAll();
		return new ResponseEntity(exercicios, HttpStatus.OK);
	}

}
