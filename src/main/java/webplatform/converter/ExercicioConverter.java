package webplatform.converter;

import java.util.HashSet;

import org.springframework.util.CollectionUtils;

import webplatform.model.ExercicioModel;
import webplatform.model.GrammarDefinicaoModel;
import webplatform.model.ReadingQuestaoModel;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.ReadingQuestao;

public class ExercicioConverter {

	public static final Exercicio convert(ExercicioModel exercicioModel) {
		Exercicio exercicio = new Exercicio();
		if (exercicioModel.getIdExercicio() != 0) {
			exercicio.setIdExercicio(exercicioModel.getIdExercicio());
		}
		exercicio.setNivel(exercicioModel.getNivel());
		exercicio.setValorNotaMaxima(Integer.parseInt(exercicioModel.getValorNotaMaxima()));

		// TODO: Remover parte de questoes
		// if (!CollectionUtils.isEmpty(exercicioModel.getQuestoes())) {
		//
		// exercicio.setQuestoes(new HashSet<Questao>());
		//
		// for (QuestaoModel questaoModel : exercicioModel.getQuestoes()) {
		// Questao questao = QuestaoConverter.convert(questaoModel);
		// exercicio.getQuestoes().add(questao);
		// }
		//
		// }

		if (!CollectionUtils.isEmpty(exercicioModel.getGrammarDefinicoes())) {

			exercicio.setGrammarDefinicoes(new HashSet<GrammarDefinicao>());

			for (GrammarDefinicaoModel definicao : exercicioModel.getGrammarDefinicoes()) {
				GrammarDefinicao definicaoTemp = GrammarDefinicaoConverter.convert(definicao);
				exercicio.getGrammarDefinicoes().add(definicaoTemp);
			}
		}

		if (!CollectionUtils.isEmpty(exercicioModel.getReadingQuestoes())) {

			exercicio.setReadingQuestoes(new HashSet<ReadingQuestao>());

			for (ReadingQuestaoModel questao : exercicioModel.getReadingQuestoes()) {
				ReadingQuestao questaoTemp = ReadingQuestaoConverter.convert(questao);
				exercicio.getReadingQuestoes().add(questaoTemp);
			}

		}

		return exercicio;
	}
}
