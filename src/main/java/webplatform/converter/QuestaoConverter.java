package webplatform.converter;

import java.util.HashSet;

import webplatform.model.AlternativaModel;
import webplatform.model.QuestaoModel;
import webplatform.model.entity.Alternativa;
import webplatform.model.entity.Questao;

public class QuestaoConverter {

	public static final Questao convert(QuestaoModel questaoModel) {
		Questao questao = new Questao();
		if (questaoModel.getIdQuestao() != 0) {
			questao.setIdQuestao(questaoModel.getIdQuestao());
		}
		questao.setPergunta(questaoModel.getPergunta());
		questao.setValorNota(Integer.parseInt(questaoModel.getValorNota()));

		if (questaoModel.getAlternativas() != null && !questaoModel.getAlternativas().isEmpty()) {

			questao.setAlternativas(new HashSet<Alternativa>());

			for (AlternativaModel alternativaModel : questaoModel.getAlternativas()) {
				Alternativa alternativa = AlternativaConverter.convert(alternativaModel);
				questao.getAlternativas().add(alternativa);
			}

		}

		return questao;
	}
}
