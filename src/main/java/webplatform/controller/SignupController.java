package webplatform.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webplatform.dao.AlunoDao;
import webplatform.dao.PaisDao;
import webplatform.dao.ProfessorDao;
import webplatform.model.UserModel;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Pais;
import webplatform.model.entity.Professor;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class SignupController {

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private ProfessorDao professorDao;

	@Autowired
	private PaisDao paisDao;

	/**
	 * Populate the registered user according to the chosen profile in ALUNO or
	 * PROFESSOR table
	 * 
	 * @param userModel
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity signup(@RequestBody UserModel userModel) {

		// It will populate the record in different tables depending on the
		// profile
		if (userModel.getTipoConta().equals("Student")) {

			Aluno aluno = new Aluno(null, null, userModel.getNome(), userModel.getEmail(), userModel.getPassword(),
					null, null, userModel.getSobrenome(), userModel.getGenero(), userModel.getPais());
			try {
				alunoDao.saveOrUpdate(aluno);
			} catch (ConstraintViolationException e) {
				return new ResponseEntity(aluno, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {

			Professor professor = new Professor(null, userModel.getNome(), userModel.getEmail(),
					userModel.getPassword(), null, null, null, userModel.getSobrenome(), userModel.getGenero(),
					userModel.getEspecialidade(), userModel.getNomeInstituicao(), userModel.getPais());
			try {
				professorDao.saveOrUpdate(professor);
			} catch (ConstraintViolationException e) {
				return new ResponseEntity(professor, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity(userModel, HttpStatus.OK);

	}

	/**
	 * Search all the options related to the question selected
	 * 
	 * @param questionId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/signup/getAllCountries", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCountries() {
		List<Pais> paises = paisDao.listAll();
		return new ResponseEntity(paises, HttpStatus.OK);
	}
}
