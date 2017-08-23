package webplatform.converter;

import java.util.HashSet;

import webplatform.model.ExercicioModel;
import webplatform.model.QuestaoModel;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.Questao;

public class ExercicioConverter {

	public static final Exercicio convert(ExercicioModel exercicioModel) {
		Exercicio exercicio = new Exercicio();
		if (exercicioModel.getIdExercicio() != 0) {
			exercicio.setIdExercicio(exercicioModel.getIdExercicio());
		}
		exercicio.setNome(exercicioModel.getNome());
		exercicio.setNivel(exercicioModel.getNivel());
		exercicio.setValorNotaMaxima(Integer.parseInt(exercicioModel.getValorNotaMaxima()));

		if (exercicioModel.getQuestoes() != null && !exercicioModel.getQuestoes().isEmpty()) {

			exercicio.setQuestoes(new HashSet<Questao>());

			for (QuestaoModel questaoModel : exercicioModel.getQuestoes()) {
				Questao questao = QuestaoConverter.convert(questaoModel);
				exercicio.getQuestoes().add(questao);
			}

		}

		return exercicio;
	}
}
