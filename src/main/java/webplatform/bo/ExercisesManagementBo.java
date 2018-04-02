package webplatform.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webplatform.converter.ExercicioConverter;
import webplatform.converter.MusicaConverter;
import webplatform.dao.AlternativaDao;
import webplatform.dao.ExercicioDao;
import webplatform.dao.GrammarDefinicaoDao;
import webplatform.dao.GrammarQuestaoDao;
import webplatform.dao.MusicaDao;
import webplatform.dao.QuestaoDao;
import webplatform.model.ExercicioModel;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.Musica;
import webplatform.model.entity.Professor;
import webplatform.model.entity.Questao;

@Service
public class ExercisesManagementBo {

	@Autowired
	private MusicaDao musicaDao;

	@Autowired
	private ExercicioDao exercicioDao;

	@Autowired
	private QuestaoDao questaoDao;

	@Autowired
	private AlternativaDao alternativaDao;

	@Autowired
	private GrammarDefinicaoDao grammarDefinicaoDao;

	@Autowired
	private GrammarQuestaoDao grammarQuestaoDao;

	@Transactional
	public Exercicio saveExercise(ExercicioModel exercicioModel) {
		Musica musica = MusicaConverter.convert(exercicioModel.getMusica());
		Exercicio exercicio = ExercicioConverter.convert(exercicioModel);
		exercicio.setProfessor(new Professor(exercicioModel.getProfessorId()));
		exercicio.setMusica(musicaDao.saveOrUpdate(musica));
		exercicioDao.saveOrUpdate(exercicio);

		for (Questao questao : exercicio.getQuestoes()) {
			questao.setExercicio(exercicio);
			questaoDao.saveOrUpdate(questao);

			for (Alternativa alternativa : questao.getAlternativas()) {
				alternativa.setQuestao(questao);
				alternativaDao.saveOrUpdate(alternativa);
			}
		}

		for (GrammarDefinicao definicao : exercicio.getGrammarDefinicoes()) {
			definicao.setExercicio(exercicio);
			grammarDefinicaoDao.saveOrUpdate(definicao);

			for (GrammarQuestao grammarQuestao : definicao.getQuestoes()) {
				grammarQuestao.setDefinicaoResposta(definicao);
				grammarQuestaoDao.saveOrUpdate(grammarQuestao);
			}
		}

		return exercicio;
	}
}
