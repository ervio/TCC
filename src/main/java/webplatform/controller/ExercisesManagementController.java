package webplatform.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import webplatform.bo.ExercisesManagementBo;
import webplatform.dao.AlternativaDao;
import webplatform.dao.AlunoDao;
import webplatform.dao.ExercicioAlunoDao;
import webplatform.dao.ExercicioDao;
import webplatform.dao.ImagemDao;
import webplatform.dao.MusicaDao;
import webplatform.dao.QuestaoDao;
import webplatform.model.ExercicioModel;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.Questao;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class ExercisesManagementController {

	@Autowired
	private ExercisesManagementBo bo;

	@Autowired
	private ExercicioDao exercicioDao;

	@Autowired
	private QuestaoDao questaoDao;

	@Autowired
	private AlternativaDao alternativaDao;

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private ExercicioAlunoDao exercicioAlunoDao;

	@Autowired
	private MusicaDao musicaDao;

	@Autowired
	private ImagemDao imagemDao;

	/**
	 * The method saves the exercises including the music of it, the questions and
	 * their alternatives
	 * 
	 * @param exercicioModel
	 * @return
	 */
	@RequestMapping(value = "/saveExercise", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveExercise(@RequestBody ExercicioModel exercicioModel) {
		Exercicio exercicio = bo.saveExercise(exercicioModel);
		return new ResponseEntity(exercicio, HttpStatus.OK);
	}

	/**
	 * Search all exercises created by the logged teacher
	 * 
	 * @param professorId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchAllExercises/{professorId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchExercisesByTeacher(@PathVariable("professorId") String professorId) {
		List<Exercicio> exercicios = exercicioDao.findByTeacher(Long.parseLong(professorId));
		return new ResponseEntity(exercicios, HttpStatus.OK);
	}

	/**
	 * Search all the questions related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/searchQuestionsByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchQuestionsByExercise(@PathVariable("exerciseId") String exerciseId) {
		List<Questao> exercicios = questaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(exercicios, HttpStatus.OK);
	}

	/**
	 * Search all the options related to the question selected
	 * 
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value = "/searchOptionsByQuestion/{questionId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchOptionsByQuestion(@PathVariable("questionId") String questionId) {
		List<Alternativa> alternativas = alternativaDao.findByQuestao(Long.parseLong(questionId));
		return new ResponseEntity(alternativas, HttpStatus.OK);
	}

	/**
	 * Delete all the selected alternatives from the question
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteAlternatives/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteAlternatives(@PathVariable String[] ids) {
		for (String alternativeId : ids) {
			alternativaDao.delete(new Alternativa(Long.parseLong(alternativeId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the questions and all the alternatives related to them
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteQuestions/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteQuestions(@PathVariable String[] ids) {
		for (String questionId : ids) {
			List<Alternativa> alternativas = alternativaDao.findByQuestao(Long.parseLong(questionId));

			for (Alternativa alternativa : alternativas) {
				alternativaDao.delete(alternativa);
			}

			questaoDao.delete(new Questao(Long.parseLong(questionId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the exercise, the questions of it and also all the alternatives
	 * related to the questions
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/deleteExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteExercise(@PathVariable("exerciseId") String exerciseId) {
		List<Questao> questoes = questaoDao.findByExercise(Long.parseLong(exerciseId));

		for (Questao questao : questoes) {

			List<Alternativa> alternativas = alternativaDao.findByQuestao(questao.getIdQuestao());

			for (Alternativa alternativa : alternativas) {
				alternativaDao.delete(alternativa);
			}

			questaoDao.delete(questao);
		}

		imagemDao.deleteAll(imagemDao.findByExercise(Long.parseLong(exerciseId)));
		exercicioDao.delete(new Exercicio(Long.parseLong(exerciseId)));
		musicaDao.delete(questoes.get(0).getExercicio().getMusica());
		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * Search all the students associated to the logged teacher
	 * 
	 * @param name
	 * @param email
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/searchStudentsToAssign/{name}/{email}/{teacherId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchStudents(@PathVariable("name") String name,
			@PathVariable("email") String email, @PathVariable("teacherId") String teacherId) {
		List<Aluno> students = alunoDao.findByNameAndEmailAndTeacher(name, email, Long.parseLong(teacherId));
		return new ResponseEntity(students, HttpStatus.OK);
	}

	/**
	 * Assign the exercise to the selected students
	 * 
	 * @param studentsIds
	 * @param idExercicio
	 * @return
	 */
	@RequestMapping(value = "/assignExercise/{studentsIds}/{idExercicio}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity assignExercise(@PathVariable String[] studentsIds,
			@PathVariable String idExercicio) {
		for (String studentId : studentsIds) {
			ExercicioAluno exercicioAluno = new ExercicioAluno();
			exercicioAluno.setAluno(new Aluno(Long.parseLong(studentId)));
			exercicioAluno.setExercicio(new Exercicio(Long.parseLong(idExercicio)));
			exercicioAlunoDao.saveOrUpdate(exercicioAluno);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody String saveUserDataAndFile(@RequestParam(value = "file") MultipartFile file,
			@RequestParam("idExercicio") String idExercicio, @RequestParam("nomeImagem") String nomeImagem) {
		System.out.println("Cacete: ");
		Imagem imagem = new Imagem();
		try {
			imagem.setBytes(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagem.setExercicio(new Exercicio(Long.parseLong(idExercicio)));
		imagem.setNome(nomeImagem);
		imagemDao.saveOrUpdate(imagem);
		return "";
	}
}
