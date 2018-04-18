package webplatform.converter;

import java.util.ArrayList;

import org.springframework.util.CollectionUtils;

import webplatform.model.PronunciationQuestaoModel;
import webplatform.model.PronunciationQuestaoParteModel;
import webplatform.model.entity.PronunciationQuestao;
import webplatform.model.entity.PronunciationQuestaoParte;

public class PronunciationQuestaoConverter {

	public static final PronunciationQuestao convert(PronunciationQuestaoModel pronunciationQuestaoModel) {
		PronunciationQuestao questao = new PronunciationQuestao();
		if (pronunciationQuestaoModel.getId() != null) {
			questao.setId(pronunciationQuestaoModel.getId());
		}

		if (!CollectionUtils.isEmpty(pronunciationQuestaoModel.getPronunciationQuestaoPartes())) {

			questao.setPronunciationQuestaoPartes(new ArrayList<PronunciationQuestaoParte>());

			for (PronunciationQuestaoParteModel pronunciationQuestaoParteModel : pronunciationQuestaoModel
					.getPronunciationQuestaoPartes()) {
				PronunciationQuestaoParte questaoParte = new PronunciationQuestaoParte(
						pronunciationQuestaoParteModel.getId(), null, pronunciationQuestaoParteModel.getDescricao(),
						pronunciationQuestaoParteModel.getTipo(), pronunciationQuestaoParteModel.getSequencia());
				questao.getPronunciationQuestaoPartes().add(questaoParte);
			}

		}

		return questao;
	}
}
