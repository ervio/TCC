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
import webplatform.model.entity.ConviteId;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class InvitationController {

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private ConviteDao conviteDao;

	/**
	 * Search all the students that are not associated to any teacher yet
	 * 
	 * @param name
	 * @param email
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/searchStudents/{name}/{email}/{teacherId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchStudents(@PathVariable("name") String name,
			@PathVariable("email") String email, @PathVariable("teacherId") String teacherId) {
		List<Aluno> students = alunoDao.findByNameAndEmailWithoutTeacher(name, email);
		return new ResponseEntity(students, HttpStatus.OK);
	}

	/**
	 * Send invitations to the selected students
	 * 
	 * @param ids
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/sendInvites/{ids}/{teacherId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity sendInvites(@PathVariable String[] ids, @PathVariable String teacherId) {
		for (String studentId : ids) {
			List<Convite> invitationList = conviteDao.findByTeacherAndStudent(Long.parseLong(teacherId),
					Long.parseLong(studentId));
			if (!invitationList.isEmpty()) {
				conviteDao.delete(invitationList.get(0));
			}

			Convite invitation = new Convite();
			invitation.setId(new ConviteId(Long.parseLong(teacherId), Long.parseLong(studentId)));
			conviteDao.saveOrUpdate(invitation);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
