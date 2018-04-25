package webplatform.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webplatform.dao.ExercicioDao;
import webplatform.dao.ForumPostDao;
import webplatform.model.ForumPostModel;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ForumPost;
import webplatform.model.entity.Professor;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class ForumController {

	@Autowired
	private ExercicioDao exercicioDao;

	@Autowired
	private ForumPostDao forumPostDao;

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

	/**
	 * Search all posts related to the exercise topic
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/getForumPosts/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getForumPosts(@PathVariable("exerciseId") String exerciseId) {
		List<ForumPost> posts = forumPostDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(posts, HttpStatus.OK);
	}

	/**
	 * Post a new comment to the exercise topic
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/replyPost", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveExercise(@RequestBody ForumPostModel forumPostModel) {
		ForumPost post = new ForumPost();
		post.setTexto(forumPostModel.getTexto());
		post.setDataPostagem(new Date());
		post.setExercicio(new Exercicio(forumPostModel.getExercicio().getIdExercicio()));

		if (forumPostModel.getAluno() != null) {
			post.setAluno(new Aluno(forumPostModel.getAluno().getId()));
		} else {
			post.setProfessor(new Professor(forumPostModel.getProfessor().getId()));
		}

		if (forumPostModel.getPostRespondido() != null) {
			post.setPostRespondido(new ForumPost(forumPostModel.getPostRespondido().getId()));
		}

		forumPostDao.saveOrUpdate(post);

		List<ForumPost> posts = forumPostDao.findByExercise(forumPostModel.getExercicio().getIdExercicio());

		return new ResponseEntity(posts, HttpStatus.OK);
	}
}
