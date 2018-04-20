package webplatform.converter;

import java.util.ArrayList;

import org.springframework.util.CollectionUtils;

import webplatform.model.ReadingAlternativaModel;
import webplatform.model.ReadingQuestaoModel;
import webplatform.model.entity.ReadingAlternativa;
import webplatform.model.entity.ReadingQuestao;

public class ReadingQuestaoConverter {

	public static final ReadingQuestao convert(ReadingQuestaoModel readingQuestaoModel) {
		ReadingQuestao questao = new ReadingQuestao();
		if (readingQuestaoModel.getId() != null) {
			questao.setId(readingQuestaoModel.getId());
		}
		questao.setPergunta(readingQuestaoModel.getPergunta());
		questao.setSequencia(readingQuestaoModel.getSequencia());

		if (!CollectionUtils.isEmpty(readingQuestaoModel.getReadingAlternativas())) {

			questao.setReadingAlternativas(new ArrayList<ReadingAlternativa>());

			for (ReadingAlternativaModel readingAlternativaModel : readingQuestaoModel.getReadingAlternativas()) {
				ReadingAlternativa alternativa = new ReadingAlternativa(readingAlternativaModel.getId(), null,
						readingAlternativaModel.getDescricao(), readingAlternativaModel.getCorreta(),
						readingAlternativaModel.getSequencia());
				questao.getReadingAlternativas().add(alternativa);
			}

		}

		return questao;
	}

}
