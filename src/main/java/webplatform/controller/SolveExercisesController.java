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

import webplatform.dao.AlternativaDao;
import webplatform.dao.ExercicioAlunoDao;
import webplatform.dao.ExercicioAlunoRespostaDao;
import webplatform.dao.GrammarQuestaoDao;
import webplatform.dao.ImagemDao;
import webplatform.dao.QuestaoDao;
import webplatform.model.AlternativaModel;
import webplatform.model.ExercicioAlunoModel;
import webplatform.model.QuestaoModel;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.ExercicioAlunoResposta;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.Questao;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
public class SolveExercisesController {

	@Autowired
	private ExercicioAlunoDao exercicioAlunoDao;

	@Autowired
	private QuestaoDao questaoDao;

	@Autowired
	private AlternativaDao alternativaDao;

	@Autowired
	private ExercicioAlunoRespostaDao exercicioAlunoRespostaDao;

	@Autowired
	private ImagemDao imagemDao;

	@Autowired
	private GrammarQuestaoDao grammarQuestaoDao;

	/**
	 * Search all the exercises to be resolved by the logged student
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/searchExercisesNotResolved/{studentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchExercises(@PathVariable("studentId") String studentId) {
		List<ExercicioAluno> exercisesToResolve = exercicioAlunoDao
				.findNotResolvedByStudentId(Long.parseLong(studentId));
		return new ResponseEntity(exercisesToResolve, HttpStatus.OK);
	}

	/**
	 * Update the exercise data as dataInicio value, chances and nota
	 * 
	 * @param exercicioAluno
	 * @return
	 */
	@RequestMapping(value = "/updateExercise", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateExercise(@RequestBody ExercicioAluno exercicioAluno) {
		exercicioAlunoDao.saveOrUpdate(exercicioAluno);
		return new ResponseEntity(HttpStatus.OK);
	}

	// TODO: Remover método abaixo
	/**
	 * Search all questions related to the exercise being resolved by the student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchQuestions/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchQuestions(@PathVariable("exerciseId") String exerciseId) {
		List<Questao> questionsToResolve = questaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(questionsToResolve, HttpStatus.OK);
	}

	/**
	 * Search all vocabulary pictures related to the exercise being resolved by the
	 * student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchVocabularyPictures/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchVocabularyPictures(@PathVariable("exerciseId") String exerciseId) {
		List<Imagem> imagens = imagemDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(imagens, HttpStatus.OK);
	}

	/**
	 * Search all grammar questions related to the exercise being resolved by the
	 * student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchGrammarQuestions/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchGrammarQuestions(@PathVariable("exerciseId") String exerciseId) {
		List<GrammarQuestao> grammarQuestions = grammarQuestaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(grammarQuestions, HttpStatus.OK);
	}

	/**
	 * Search all alternatives related to the question that is being loaded
	 * 
	 * @param questionId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchAlternativesByQuestion/{questionId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchAlternativesByQuestion(@PathVariable("questionId") String questionId) {
		List<Alternativa> questionAlternatives = alternativaDao.findByQuestao(Long.parseLong(questionId));
		return new ResponseEntity(questionAlternatives, HttpStatus.OK);
	}

	/**
	 * Submit the questions resolved by the logged student
	 * 
	 * @param exercicioAlunoModel
	 * @return
	 */
	@RequestMapping(value = "/submitQuestions", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity submitQuestions(@RequestBody ExercicioAlunoModel exercicioAlunoModel) {
		int scoreSum = 0;

		for (QuestaoModel question : exercicioAlunoModel.getQuestions()) {

			for (AlternativaModel alternative : question.getAlternativas()) {
				if (alternative.getResposta().equals(question.getResposta())) {

					ExercicioAlunoResposta exercicioAlunoResposta = new ExercicioAlunoResposta();
					exercicioAlunoResposta.setAlternativa(new Alternativa(alternative.getIdAlternativa()));
					exercicioAlunoResposta
							.setExercicioAluno(new ExercicioAluno(exercicioAlunoModel.getIdExercicioAluno()));
					exercicioAlunoResposta.setQuestao(new Questao(question.getIdQuestao()));

					// If the alternative is correct the score is increased
					if (alternative.isCorreta()) {
						exercicioAlunoResposta.setNota(Integer.parseInt(question.getValorNota()));
						scoreSum = scoreSum + Integer.parseInt(question.getValorNota());
					} else {
						exercicioAlunoResposta.setNota(0);
					}

					exercicioAlunoRespostaDao.saveOrUpdate(exercicioAlunoResposta);
				}
			}

		}

		ExercicioAluno exercicioAluno = exercicioAlunoDao.findById(exercicioAlunoModel.getIdExercicioAluno());
		if (exercicioAluno.getChances() == 0) {
			exercicioAluno.setChances(1);
		}
		exercicioAluno.setNota(scoreSum);
		exercicioAluno.setDataFim(new Date());
		exercicioAlunoDao.saveOrUpdate(exercicioAluno);
		exercicioAlunoModel.setNota(scoreSum);

		return new ResponseEntity(exercicioAlunoModel, HttpStatus.OK);
	}
}
