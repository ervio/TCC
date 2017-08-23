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
import webplatform.dao.ConviteDao;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Convite;
import webplatform.model.entity.Professor;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class MainController {

	@Autowired
	private ConviteDao conviteDao;

	@Autowired
	private AlunoDao alunoDao;

	/**
	 * The method returns the invitations sent to the logged user
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/searchInvites/{studentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchInvites(@PathVariable String studentId) {
		List<Convite> invitations = conviteDao.findByStudent(Long.parseLong(studentId));
		return new ResponseEntity(invitations, HttpStatus.OK);
	}

	/**
	 * The method associates the teacher who sent the invitation to the logged
	 * user
	 * 
	 * @param teacherId
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/acceptInvitation/{teacherId}/{studentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity acceptInvitation(@PathVariable String teacherId,
			@PathVariable String studentId) {
		List<Convite> invitations = conviteDao.findByStudent(Long.parseLong(studentId));
		conviteDao.deleteAll(invitations);
		Aluno student = alunoDao.findById(Long.parseLong(studentId));
		student.setProfessor(new Professor(Long.parseLong(teacherId)));
		alunoDao.saveOrUpdate(student);

		return new ResponseEntity(invitations, HttpStatus.OK);
	}
}
