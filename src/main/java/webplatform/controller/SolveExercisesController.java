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
import webplatform.dao.GrammarRespostaDao;
import webplatform.dao.ImagemDao;
import webplatform.dao.PronunciationQuestaoDao;
import webplatform.dao.PronunciationQuestaoParteDao;
import webplatform.dao.PronunciationRespostaDao;
import webplatform.dao.QuestaoDao;
import webplatform.dao.ReadingAlternativaDao;
import webplatform.dao.ReadingQuestaoDao;
import webplatform.dao.ReadingRespostaDao;
import webplatform.dao.VocabularyRespostaDao;
import webplatform.model.AlternativaModel;
import webplatform.model.ExercicioAlunoModel;
import webplatform.model.GrammarQuestaoModel;
import webplatform.model.ImagemModel;
import webplatform.model.PronunciationQuestaoModel;
import webplatform.model.PronunciationQuestaoParteModel;
import webplatform.model.QuestaoModel;
import webplatform.model.ReadingAlternativaModel;
import webplatform.model.ReadingQuestaoModel;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.ExercicioAlunoResposta;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.GrammarResposta;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.PronunciationQuestaoParte;
import webplatform.model.entity.PronunciationResposta;
import webplatform.model.entity.Questao;
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
	private QuestaoDao questaoDao;

	@Autowired
	private AlternativaDao alternativaDao;

	@Autowired
	private ExercicioAlunoRespostaDao exercicioAlunoRespostaDao;

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

	// TODO: REMOVER
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

			if (grammarQuestaoModel.getDefinicaoResposta().getId() == grammarResposta.getGrammarQuestaoResposta()
					.getId()) {
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
				if (readingAlternativaModel.getCorreta()
						&& readingAlternativaModel.getId() == readingResposta.getReadingQuestaoResposta().getId()) {
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

		return new ResponseEntity(exercicioAlunoModel, HttpStatus.OK);

	}

	// TODO: Remover
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
