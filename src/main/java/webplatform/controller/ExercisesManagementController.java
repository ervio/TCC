package webplatform.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

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
import webplatform.dao.GrammarDefinicaoDao;
import webplatform.dao.GrammarQuestaoDao;
import webplatform.dao.ImagemDao;
import webplatform.dao.MusicaDao;
import webplatform.dao.PronunciationQuestaoDao;
import webplatform.dao.PronunciationQuestaoParteDao;
import webplatform.dao.QuestaoDao;
import webplatform.dao.ReadingAlternativaDao;
import webplatform.dao.ReadingQuestaoDao;
import webplatform.model.ExercicioModel;
import webplatform.model.ImagemModel;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.Aluno;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.PronunciationQuestaoParte;
import webplatform.model.entity.Questao;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class ExercisesManagementController {

	@Autowired
	private ExercisesManagementBo bo;

	@Autowired
	private ExercicioDao exercicioDao;

	// TODO: Remover
	@Autowired
	private QuestaoDao questaoDao;

	// TODO: Remover
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

	@Autowired
	private GrammarDefinicaoDao grammarDefinicaoDao;

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
	@RequestMapping(value = "/searchGrammarDefinitionsByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchGrammarDefinitionsByExercise(
			@PathVariable("exerciseId") String exerciseId) {
		List<GrammarDefinicao> definicoes = grammarDefinicaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(definicoes, HttpStatus.OK);
	}

	/**
	 * Search all the questions related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/searchGrammarAlternativesByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchGrammarAlternativesByExercise(
			@PathVariable("exerciseId") String exerciseId) {
		List<GrammarQuestao> questoes = grammarQuestaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(questoes, HttpStatus.OK);
	}

	/**
	 * Search all the reading questions related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/searchReadingQuestionsByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchReadingQuestionsByExercise(
			@PathVariable("exerciseId") String exerciseId) {
		List<ReadingQuestao> questoes = readingQuestaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(questoes, HttpStatus.OK);
	}

	/**
	 * Search all the questions related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/searchReadingAlternativesByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchReadingAlternativesByExercise(
			@PathVariable("exerciseId") String exerciseId) {
		List<ReadingAlternativa> alternativas = readingAlternativaDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(alternativas, HttpStatus.OK);
	}

	/**
	 * Search all the pronunciation questions related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchPronunciationQuestionsByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchPronunciationQuestionsByExercise(
			@PathVariable("exerciseId") String exerciseId) {
		List<PronunciationQuestao> questoes = pronunciationQuestaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(questoes, HttpStatus.OK);
	}

	/**
	 * Search all the pronunciation questions parts related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchPronunciationQuestionsPartsByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchPronunciationQuestionsPartsByExercise(
			@PathVariable("exerciseId") String exerciseId) {
		List<PronunciationQuestaoParte> questoes = pronunciationQuestaoParteDao
				.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(questoes, HttpStatus.OK);
	}

	/**
	 * Search all the pictures related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "/searchPicturesByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchPicturesByExercise(@PathVariable("exerciseId") String exerciseId,
			HttpServletResponse response) throws IOException {
		List<Imagem> listaImagens = imagemDao.findByExercise(Long.parseLong(exerciseId));
		List<ImagemModel> lista = new ArrayList<ImagemModel>();

		for (Imagem imagem : listaImagens) {
			// File file = new File(System.getProperty("java.io.tmpdir") + "/" +
			// imagem.getNome());
			// FileOutputStream fOut = new FileOutputStream(file);
			// fOut.write(imagem.getBytes());
			// fOut.close();
			ImagemModel imagemModel = new ImagemModel();
			imagemModel.setId(imagem.getId());
			imagemModel.setBase64(DatatypeConverter.printBase64Binary(imagem.getBytes()));
			imagemModel.setNome(imagem.getNome());
			imagemModel.setBytes(imagem.getBytes());
			lista.add(imagemModel);
		}

		return new ResponseEntity(lista, HttpStatus.OK);
	}

	// TODO: Remover
	/**
	 * Search all the definitions related to the exercise selected
	 * 
	 * @param exerciseId
	 * @return
	 */
	@RequestMapping(value = "/searchQuestionsByExercise/{exerciseId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchQuestionsByExercise(@PathVariable("exerciseId") String exerciseId) {
		List<Questao> exercicios = questaoDao.findByExercise(Long.parseLong(exerciseId));
		return new ResponseEntity(exercicios, HttpStatus.OK);
	}

	// TODO: Remover
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

	// TODO: Remover
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

	// TODO: Remover
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
	 * Delete the pictures related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletePictures/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deletePictures(@PathVariable String[] ids) {
		for (String pictureId : ids) {
			imagemDao.delete(new Imagem(Long.parseLong(pictureId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the grammar alternatives related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteGrammarAlternatives/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteGrammarAlternatives(@PathVariable String[] ids) {
		for (String alternativeId : ids) {
			grammarQuestaoDao.delete(new GrammarQuestao(Long.parseLong(alternativeId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the grammar definitions related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteGrammarDefinitions/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteGrammarDefinitions(@PathVariable String[] ids) {
		for (String definitionId : ids) {
			grammarDefinicaoDao.delete(new GrammarDefinicao(Long.parseLong(definitionId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the reading alternatives and questions related to the exercise
	 * selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteReadingAlternativesAndQuestions/{readingAlternativesToDelete}/{readingQuestionsToDelete}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteReadingAlternativesAndQuestions(
			@PathVariable("readingAlternativesToDelete") String[] readingAlternativesToDelete,
			@PathVariable("readingQuestionsToDelete") String[] readingQuestionsToDelete) {
		for (String alternativeId : readingAlternativesToDelete) {
			readingAlternativaDao.delete(new ReadingAlternativa(Long.parseLong(alternativeId)));
		}

		for (String questionId : readingQuestionsToDelete) {
			readingQuestaoDao.delete(new ReadingQuestao(Long.parseLong(questionId)));
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the reading alternatives related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteReadingAlternatives/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteReadingAlternatives(@PathVariable String[] ids) {
		for (String readingAlternativaId : ids) {
			readingAlternativaDao.delete(new ReadingAlternativa(Long.parseLong(readingAlternativaId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the reading questions related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteReadingQuestions/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deleteReadingQuestions(@PathVariable String[] ids) {
		for (String readingQuestionId : ids) {
			readingQuestaoDao.delete(new ReadingQuestao(Long.parseLong(readingQuestionId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the pronunciation questions and parts related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletePronunciationPartsAndQuestions/{pronunciationQuestionPartsToDelete}/{pronunciationQuestionsToDelete}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deletePronunciationPartsAndQuestions(
			@PathVariable("pronunciationQuestionPartsToDelete") String[] pronunciationQuestionPartsToDelete,
			@PathVariable("pronunciationQuestionsToDelete") String[] pronunciationQuestionsToDelete) {
		for (String questionPartId : pronunciationQuestionPartsToDelete) {
			pronunciationQuestaoParteDao.delete(new PronunciationQuestaoParte(Long.parseLong(questionPartId)));
		}

		for (String questionId : pronunciationQuestionsToDelete) {
			pronunciationQuestaoDao.delete(new PronunciationQuestao(Long.parseLong(questionId)));
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the pronunciation parts related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletePronunciationQuestionParts/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deletePronunciationQuestionParts(@PathVariable String[] ids) {
		for (String questionPartId : ids) {
			pronunciationQuestaoParteDao.delete(new PronunciationQuestaoParte(Long.parseLong(questionPartId)));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete the reading questions related to the exercise selected
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletePronunciationQuestions/{ids}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity deletePronunciationQuestions(@PathVariable String[] ids) {
		for (String questionId : ids) {
			pronunciationQuestaoDao.delete(new PronunciationQuestao(Long.parseLong(questionId)));
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

	@RequestMapping(value = "/saveFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody ResponseEntity<MultipartFile> saveFile(@RequestParam(value = "file") MultipartFile file,
			@RequestParam("idExercicio") String idExercicio, @RequestParam("nomeImagem") String nomeImagem)
			throws IllegalStateException, IOException {
		Imagem imagem = new Imagem();
		imagem.setBytes(file.getBytes());
		imagem.setExercicio(new Exercicio(Long.parseLong(idExercicio)));
		imagem.setNome(nomeImagem);
		imagemDao.saveOrUpdate(imagem);

		ImagemModel imagemModel = new ImagemModel();
		imagemModel.setId(imagem.getId());
		imagemModel.setBase64(DatatypeConverter.printBase64Binary(imagem.getBytes()));
		imagemModel.setNome(imagem.getNome());
		imagemModel.setBytes(imagem.getBytes());

		return new ResponseEntity(imagemModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateFile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<MultipartFile> updateFile(@RequestParam("idExercicio") String idExercicio,
			@RequestParam("nomeImagem") String nomeImagem, @RequestParam("id") Long id)
			throws IllegalStateException, IOException {
		Imagem imagem = imagemDao.findById(id);
		imagem.setNome(nomeImagem);
		imagemDao.saveOrUpdate(imagem);

		ImagemModel imagemModel = new ImagemModel();
		imagemModel.setId(imagem.getId());
		imagemModel.setBase64(DatatypeConverter.printBase64Binary(imagem.getBytes()));
		imagemModel.setNome(imagem.getNome());
		imagemModel.setBytes(imagem.getBytes());

		return new ResponseEntity(imagemModel, HttpStatus.OK);
	}
}
