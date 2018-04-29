package webplatform.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webplatform.converter.ExercicioConverter;
import webplatform.converter.MusicaConverter;
import webplatform.dao.ExercicioDao;
import webplatform.dao.GrammarDefinicaoDao;
import webplatform.dao.GrammarQuestaoDao;
import webplatform.dao.MusicaDao;
import webplatform.dao.PronunciationQuestaoDao;
import webplatform.dao.PronunciationQuestaoParteDao;
import webplatform.dao.ReadingAlternativaDao;
import webplatform.dao.ReadingQuestaoDao;
import webplatform.model.ExercicioModel;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.Musica;
import webplatform.model.entity.Professor;
import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.PronunciationQuestaoParte;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;

@Service
public class ExercisesManagementBo {

	@Autowired
	private MusicaDao musicaDao;

	@Autowired
	private ExercicioDao exercicioDao;

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

	@Transactional
	public Exercicio saveExercise(ExercicioModel exercicioModel) {
		Musica musica = MusicaConverter.convert(exercicioModel.getMusica());
		Exercicio exercicio = ExercicioConverter.convert(exercicioModel);
		exercicio.setProfessor(new Professor(exercicioModel.getProfessorId()));
		exercicio.setMusica(musicaDao.saveOrUpdate(musica));
		exercicioDao.saveOrUpdate(exercicio);

		for (GrammarDefinicao definicao : exercicio.getGrammarDefinicoes()) {
			definicao.setExercicio(exercicio);
			grammarDefinicaoDao.saveOrUpdate(definicao);

			for (GrammarQuestao grammarQuestao : definicao.getQuestoes()) {
				grammarQuestao.setDefinicaoResposta(definicao);
				grammarQuestaoDao.saveOrUpdate(grammarQuestao);
			}
		}

		for (ReadingQuestao questao : exercicio.getReadingQuestoes()) {
			questao.setExercicio(exercicio);
			readingQuestaoDao.saveOrUpdate(questao);

			for (ReadingAlternativa alternativa : questao.getReadingAlternativas()) {
				alternativa.setQuestao(questao);
				readingAlternativaDao.saveOrUpdate(alternativa);
			}
		}

		for (PronunciationQuestao questao : exercicio.getPronunciationQuestoes()) {
			questao.setExercicio(exercicio);
			pronunciationQuestaoDao.saveOrUpdate(questao);

			for (PronunciationQuestaoParte questaoParte : questao.getPronunciationQuestaoPartes()) {
				questaoParte.setPronunciationQuestao(questao);
				pronunciationQuestaoParteDao.saveOrUpdate(questaoParte);
			}
		}

		return exercicio;
	}
}
