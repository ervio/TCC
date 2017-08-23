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

import webplatform.dao.AlunoDao;
import webplatform.dao.ProfessorDao;
import webplatform.enums.AccountTypeEnum;
import webplatform.model.UserModel;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Professor;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class LoginController {

	@Autowired
	private ProfessorDao professorDao;

	@Autowired
	private AlunoDao alunoDao;

	/**
	 * Validates the user access in ALUNO and PROFESSOR tables using the
	 * username and password informed by the user
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login/{email}/{password}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity login(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		List<Professor> teacherList = professorDao.findByEmailAndPassword(email, password);

		// Search user in PROFESSOR table
		if (!teacherList.isEmpty()) {
			Professor teacher = teacherList.get(0);
			UserModel userModel = new UserModel(teacher.getId(), teacher.getNome(), teacher.getTelefone(),
					teacher.getEmail(), AccountTypeEnum.TEACHER.getValue(), teacher.getPassword(),
					teacher.getLogradouro(), teacher.getBairro(), teacher.getCidade(), teacher.getCep());
			return new ResponseEntity(userModel, HttpStatus.OK);

		} else {

			// Search user in ALUNO table
			List<Aluno> studentList = alunoDao.findByEmailAndPassword(email, password);
			if (!studentList.isEmpty()) {
				Aluno student = studentList.get(0);
				UserModel userModel = new UserModel(student.getId(), student.getNome(), student.getTelefone(),
						student.getEmail(), AccountTypeEnum.STUDENT.getValue(), student.getPassword(),
						student.getLogradouro(), student.getBairro(), student.getCidade(), student.getCep());
				return new ResponseEntity(userModel, HttpStatus.OK);
			} else {
				return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
