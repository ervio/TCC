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

import webplatform.dao.ExercicioAlunoDao;
import webplatform.dao.ExercicioDao;
import webplatform.dao.ForumPostDao;
import webplatform.dao.GrammarQuestaoDao;
import webplatform.dao.GrammarRespostaDao;
import webplatform.dao.ImagemDao;
import webplatform.dao.PronunciationQuestaoDao;
import webplatform.dao.PronunciationQuestaoParteDao;
import webplatform.dao.PronunciationRespostaDao;
import webplatform.dao.ReadingAlternativaDao;
import webplatform.dao.ReadingQuestaoDao;
import webplatform.dao.ReadingRespostaDao;
import webplatform.dao.VocabularyRespostaDao;
import webplatform.model.ExercicioAlunoModel;
import webplatform.model.GrammarQuestaoModel;
import webplatform.model.ImagemModel;
import webplatform.model.PronunciationQuestaoModel;
import webplatform.model.PronunciationQuestaoParteModel;
import webplatform.model.ReadingAlternativaModel;
import webplatform.model.ReadingQuestaoModel;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.ForumPost;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.GrammarResposta;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.PronunciationQuestaoParte;
import webplatform.model.entity.PronunciationResposta;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;
import webplatform.model.entity.ReadingResposta;
import webplatform.model.entity.VocabularyResposta;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
public class SolveExercisesController {

	@Autowired
	private ExercicioAlunoDao exercicioAlunoDao;

	@Autowired
	private ImagemDao imagemDao;

	@Autowired
	private GrammarQuestaoDao grammarQuestaoDao;

	@Autowired
	private ReadingQuestaoDao readingQuestaoDao;

	@Autowired
	private ReadingAlternativaDao readingAlternativaDao;

	@Autowired
	private PronunciationQuestaoDao pronunciationQuestaoDao;

	@Autowired
	private PronunciationQuestaoParteDao pronunciationQuestaoParteDao;

	@Autowired
	private VocabularyRespostaDao vocabularyRespostaDao;

	@Autowired
	private GrammarRespostaDao grammarRespostaDao;

	@Autowired
	private ReadingRespostaDao readingRespostaDao;

	@Autowired
	private PronunciationRespostaDao pronunciationRespostaDao;

	@Autowired
	private ForumPostDao forumPostDao;

	@Autowired
	private ExercicioDao exercicioDao;

	/**
	 * Search all the assigned exercises to be resolved by the logged student
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/searchAssignedExercises/{studentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchAssignedExercises(@PathVariable("studentId") String studentId) {
		List<ExercicioAluno> exercisesToResolve = exercicioAlunoDao
				.findNotResolvedByStudentId(Long.parseLong(studentId));
		return new ResponseEntity(exercisesToResolve, HttpStatus.OK);
	}

	/**
	 * Search all the exercises to be resolved by the logged student
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/searchAllNotAssignedExercises/{studentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchAllNotAssignedExercises(@PathVariable("studentId") String studentId) {
		List<Exercicio> exercises = exercicioDao.findNotAssignedExercises(Long.parseLong(studentId));
		return new ResponseEntity(exercises, HttpStatus.OK);
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
	 * Search all reading questions related to the exercise being resolved by the
	 * student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchReadingQuestions/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchReadingQuestions(@PathVariable("exerciseId") String exerciseId) {
		List<ReadingQuestao> readingQuestions = readingQuestaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(readingQuestions, HttpStatus.OK);
	}

	/**
	 * Search all reading alternatives related to the exercise being resolved by the
	 * student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchReadingAlternatives/{questionId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchReadingAlternatives(@PathVariable("questionId") String questionId) {
		List<ReadingAlternativa> readingAlternativas = readingAlternativaDao.findByQuestion(Long.parseLong(questionId));
		return new ResponseEntity(readingAlternativas, HttpStatus.OK);
	}

	/**
	 * Search all pronunciation questions related to the exercise being resolved by
	 * the student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchPronunciationQuestions/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchPronunciationQuestions(@PathVariable("exerciseId") String exerciseId) {
		List<PronunciationQuestao> pronunciationQuestions = pronunciationQuestaoDao
				.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(pronunciationQuestions, HttpStatus.OK);
	}

	/**
	 * Search all pronunciation question parts related to the exercise being
	 * resolved by the student
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchPronunciationParts/{questionId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchPronunciationParts(@PathVariable("questionId") String questionId) {
		List<PronunciationQuestaoParte> pronunciationQuestionParts = pronunciationQuestaoParteDao
				.findByQuestion(Long.parseLong(questionId));
		return new ResponseEntity(pronunciationQuestionParts, HttpStatus.OK);
	}

	/**
	 * Submit the exercise resolved by the logged student
	 * 
	 * @param exercicioAlunoModel
	 * @return
	 */
	@RequestMapping(value = "/submitExercise", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity submitExercise(@RequestBody ExercicioAlunoModel exercicioAlunoModel) {
		Integer totalQuestoes = 0;
		Integer questoesCorretas = 0;

		for (ImagemModel imagem : exercicioAlunoModel.getImagens()) {
			totalQuestoes++;
			VocabularyResposta vocabularyResposta = new VocabularyResposta();
			vocabularyResposta.setExercicioAluno(new ExercicioAluno(exercicioAlunoModel.getIdExercicioAluno()));
			vocabularyResposta.setImagem(new Imagem(imagem.getId()));
			vocabularyResposta.setImagemResposta(exercicioAlunoModel.getImagens().get(imagem.getResposta()).getNome());
			vocabularyRespostaDao.saveOrUpdate(vocabularyResposta);

			if (imagem.getNome().equals(vocabularyResposta.getImagemResposta())) {
				questoesCorretas++;
			}
		}

		for (GrammarQuestaoModel grammarQuestaoModel : exercicioAlunoModel.getGrammarQuestions()) {
			totalQuestoes++;
			GrammarResposta grammarResposta = new GrammarResposta();
			grammarResposta.setExercicioAluno(new ExercicioAluno(exercicioAlunoModel.getIdExercicioAluno()));
			grammarResposta.setGrammarQuestao(new GrammarQuestao(grammarQuestaoModel.getId()));
			grammarResposta.setGrammarQuestaoResposta(new GrammarDefinicao(
					exercicioAlunoModel.getGrammarDefinitions().get(grammarQuestaoModel.getResposta()).getId()));
			grammarRespostaDao.saveOrUpdate(grammarResposta);

			if (grammarQuestaoModel.getDefinicaoResposta().getId().longValue() == grammarResposta
					.getGrammarQuestaoResposta().getId().longValue()) {
				questoesCorretas++;
			}
		}

		for (ReadingQuestaoModel readingQuestaoModel : exercicioAlunoModel.getReadingQuestions()) {
			totalQuestoes++;
			ReadingResposta readingResposta = new ReadingResposta();
			readingResposta.setExercicioAluno(new ExercicioAluno(exercicioAlunoModel.getIdExercicioAluno()));
			readingResposta.setReadingQuestao(new ReadingQuestao(readingQuestaoModel.getId()));
			readingResposta.setReadingQuestaoResposta(new ReadingAlternativa(readingQuestaoModel.getResposta()));
			readingRespostaDao.saveOrUpdate(readingResposta);
			for (ReadingAlternativaModel readingAlternativaModel : readingQuestaoModel.getReadingAlternativas()) {
				if (readingAlternativaModel.getCorreta() && readingAlternativaModel.getId()
						.longValue() == readingResposta.getReadingQuestaoResposta().getId().longValue()) {
					questoesCorretas++;
					break;
				}
			}
		}

		for (PronunciationQuestaoModel pronunciationQuestaoModel : exercicioAlunoModel.getPronunciationQuestoes()) {
			for (PronunciationQuestaoParteModel pronunciationQuestaoParteModel : pronunciationQuestaoModel
					.getPronunciationQuestaoPartes()) {
				if (pronunciationQuestaoParteModel.getTipo().equals("Gap")) {
					totalQuestoes++;
					PronunciationResposta pronunciationResposta = new PronunciationResposta();
					pronunciationResposta
							.setExercicioAluno(new ExercicioAluno(exercicioAlunoModel.getIdExercicioAluno()));
					pronunciationResposta.setPronunciationQuestaoParte(
							new PronunciationQuestaoParte(pronunciationQuestaoParteModel.getId()));
					pronunciationResposta.setResposta(pronunciationQuestaoParteModel.getResposta());
					pronunciationRespostaDao.saveOrUpdate(pronunciationResposta);

					if (pronunciationQuestaoParteModel.getDescricao().replaceAll(" ", "").toLowerCase()
							.equalsIgnoreCase(pronunciationResposta.getResposta().replaceAll(" ", "").toLowerCase())) {
						questoesCorretas++;
					}
				}
			}
		}

		ExercicioAluno exercicioAluno = exercicioAlunoDao.findById(exercicioAlunoModel.getIdExercicioAluno());
		if (exercicioAluno.getChances() == 0) {
			exercicioAluno.setChances(1);
		}
		exercicioAluno.setDataFim(new Date());
		exercicioAluno.setWritingQuestaoResposta(exercicioAlunoModel.getWritingQuestaoResposta());
		exercicioAluno.setTotalQuestoes(totalQuestoes);
		exercicioAlunoModel.setTotalQuestoes(totalQuestoes);
		exercicioAluno.setQuestoesCorretas(questoesCorretas);
		exercicioAlunoModel.setQuestoesCorretas(questoesCorretas);
		exercicioAlunoDao.saveOrUpdate(exercicioAluno);

		ForumPost post = new ForumPost(null, exercicioAluno.getAluno(), exercicioAluno.getExercicio(), null, null,
				exercicioAluno.getWritingQuestaoResposta(), new Date());
		forumPostDao.saveOrUpdate(post);

		Exercicio exercicio = exercicioAluno.getExercicio();
		if (exercicio.getTotalPosts() == null) {
			exercicio.setTotalPosts(1);
		} else {
			exercicio.setTotalPosts(exercicio.getTotalPosts() + 1);
		}

		exercicioDao.saveOrUpdate(exercicio);

		return new ResponseEntity(exercicioAlunoModel, HttpStatus.OK);

	}

}
