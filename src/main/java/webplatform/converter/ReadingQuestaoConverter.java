package webplatform.converter;

import webplatform.model.ReadingQuestaoModel;
import webplatform.model.entity.ReadingQuestao;

public class ReadingQuestaoConverter {

	public static final ReadingQuestao convert(ReadingQuestaoModel readingQuestaoModel) {
		ReadingQuestao questao = new ReadingQuestao();
		if (readingQuestaoModel.getId() != null) {
			questao.setId(readingQuestaoModel.getId());
		}
		questao.setPergunta(readingQuestaoModel.getPergunta());

		// if (!CollectionUtils.isEmpty(grammarDefinicaoModel.getQuestoes())) {
		//
		// grammarDefinicao.setQuestoes(new HashSet<GrammarQuestao>());
		//
		// for (GrammarQuestaoModel grammarQuestaoModel :
		// grammarDefinicaoModel.getQuestoes()) {
		// GrammarQuestao grammarQuestao = new
		// GrammarQuestao(grammarQuestaoModel.getId(),
		// grammarQuestaoModel.getQuestao(), null);
		// grammarDefinicao.getQuestoes().add(grammarQuestao);
		// }
		//
		// }

		return questao;
	}

}
