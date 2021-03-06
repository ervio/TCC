package webplatform.converter;

import java.util.ArrayList;

import org.springframework.util.CollectionUtils;

import webplatform.model.ExercicioModel;
import webplatform.model.GrammarDefinicaoModel;
import webplatform.model.PronunciationQuestaoModel;
import webplatform.model.ReadingQuestaoModel;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.ReadingQuestao;

public class ExercicioConverter {

	public static final Exercicio convert(ExercicioModel exercicioModel) {
		Exercicio exercicio = new Exercicio();
		if (exercicioModel.getIdExercicio() != 0) {
			exercicio.setIdExercicio(exercicioModel.getIdExercicio());

			if (exercicioModel.getTotalPosts() != null) {
				exercicio.setTotalPosts(exercicioModel.getTotalPosts());
			}

		} else {
			exercicio.setTotalPosts(0);
		}

		exercicio.setNivel(exercicioModel.getNivel());
		exercicio.setWritingQuestao(exercicioModel.getWritingQuestao());

		if (!CollectionUtils.isEmpty(exercicioModel.getGrammarDefinicoes())) {

			exercicio.setGrammarDefinicoes(new ArrayList<GrammarDefinicao>());

			for (GrammarDefinicaoModel definicao : exercicioModel.getGrammarDefinicoes()) {
				GrammarDefinicao definicaoTemp = GrammarDefinicaoConverter.convert(definicao);
				exercicio.getGrammarDefinicoes().add(definicaoTemp);
			}
		}

		if (!CollectionUtils.isEmpty(exercicioModel.getReadingQuestoes())) {

			exercicio.setReadingQuestoes(new ArrayList<ReadingQuestao>());

			for (ReadingQuestaoModel questao : exercicioModel.getReadingQuestoes()) {
				ReadingQuestao questaoTemp = ReadingQuestaoConverter.convert(questao);
				exercicio.getReadingQuestoes().add(questaoTemp);
			}

		}

		if (!CollectionUtils.isEmpty(exercicioModel.getPronunciationQuestions())) {

			exercicio.setPronunciationQuestoes(new ArrayList<PronunciationQuestao>());

			for (PronunciationQuestaoModel questao : exercicioModel.getPronunciationQuestions()) {
				PronunciationQuestao questaoTemp = PronunciationQuestaoConverter.convert(questao);
				exercicio.getPronunciationQuestoes().add(questaoTemp);
			}

		}

		return exercicio;
	}
}
