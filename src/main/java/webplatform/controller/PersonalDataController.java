package webplatform.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import webplatform.enums.DataBaseErrorsEnum;
import webplatform.model.UserModel;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Pais;
import webplatform.model.entity.Professor;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class PersonalDataController {

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private ProfessorDao professorDao;

	@Autowired
	private PaisDao paisDao;

	/**
	 * Update the personal data of the logged user
	 * 
	 * @param userModel
	 * @return
	 */
	@RequestMapping(value = "/updateUserData", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity update(@RequestBody UserModel userModel) {

		if (userModel.getTipoConta().equals("Student")) {
			return updateStudent(userModel);
		} else {
			return updateTeacher(userModel);
		}

	}

	/**
	 * The method updates the personal data of the teacher
	 * 
	 * @param userModel
	 * @return
	 */
	private ResponseEntity updateTeacher(UserModel userModel) {
		List<Professor> professorList = professorDao.findByEmailAndPassword(userModel.getEmail(),
				userModel.getPassword());

		if (!professorList.isEmpty()) {

			Professor professor = professorList.get(0);
			professor.setNome(userModel.getNome());
			professor.setSobrenome(userModel.getSobrenome());

			if (!userModel.getEmail().equals(userModel.getNovoEmail())) {
				professor.setEmail(userModel.getNovoEmail());
			}
			if (StringUtils.stripToNull(userModel.getNovoPassword()) != null) {
				professor.setPassword(userModel.getNovoPassword());
			}

			professor.setGenero(userModel.getGenero());
			professor.setPais(userModel.getPais());
			professor.setEspecialidade(userModel.getEspecialidade());
			professor.setNomeInstituicao(userModel.getNomeInstituicao());

			try {
				professorDao.saveOrUpdate(professor);
			} catch (Exception e) {

				// There is a constraint in the database to avoid the same email
				// to be used by more than 1 person
				if (e instanceof DataIntegrityViolationException || e.getCause().getCause().getMessage()
						.contains(DataBaseErrorsEnum.EMAIL_PROFESSOR_TRIGGER.getValue())) {
					userModel.setStatus("The email '" + userModel.getNovoEmail()
							+ "' is already registered, please choose another one.");
				}

				return new ResponseEntity(userModel, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity(userModel, HttpStatus.OK);
		} else {
			userModel.setStatus("The password is wrong.");
			return new ResponseEntity(userModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * The method updates the personal data of the student
	 * 
	 * @param userModel
	 * @return
	 */
	private ResponseEntity updateStudent(UserModel userModel) {
		List<Aluno> alunoList = alunoDao.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());

		if (!alunoList.isEmpty()) {

			Aluno aluno = alunoList.get(0);
			aluno.setNome(userModel.getNome());
			aluno.setSobrenome(userModel.getSobrenome());

			if (!userModel.getEmail().equals(userModel.getNovoEmail())) {
				aluno.setEmail(userModel.getNovoEmail());
			}
			if (StringUtils.stripToNull(userModel.getNovoPassword()) != null) {
				aluno.setPassword(userModel.getNovoPassword());
			}

			aluno.setGenero(userModel.getGenero());
			aluno.setPais(userModel.getPais());

			try {
				alunoDao.saveOrUpdate(aluno);
			} catch (Exception e) {

				// There is a constraint in the database to avoid the same email
				// to be used by more than 1 person
				if (e instanceof DataIntegrityViolationException || e.getCause().getCause().getMessage()
						.contains(DataBaseErrorsEnum.EMAIL_ALUNO_TRIGGER.getValue())) {
					userModel.setStatus("The email '" + userModel.getNovoEmail()
							+ "' is already registered, please choose another one.");
				}

				return new ResponseEntity(userModel, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity(userModel, HttpStatus.OK);

		} else {
			userModel.setStatus("The password is wrong.");
			return new ResponseEntity(userModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Search all the options related to the question selected
	 * 
	 * @param questionId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/personalData/getAllCountries", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCountries() {
		List<Pais> paises = paisDao.listAll();
		return new ResponseEntity(paises, HttpStatus.OK);
	}
}
