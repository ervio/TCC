package webplatform.converter;

import java.util.ArrayList;

import org.springframework.util.CollectionUtils;

import webplatform.model.GrammarDefinicaoModel;
import webplatform.model.GrammarQuestaoModel;
import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;

public class GrammarDefinicaoConverter {

	public static final GrammarDefinicao convert(GrammarDefinicaoModel grammarDefinicaoModel) {
		GrammarDefinicao grammarDefinicao = new GrammarDefinicao();
		if (grammarDefinicaoModel.getId() != null) {
			grammarDefinicao.setId(grammarDefinicaoModel.getId());
		}
		grammarDefinicao.setDefinicao(grammarDefinicaoModel.getDefinicao());

		if (!CollectionUtils.isEmpty(grammarDefinicaoModel.getQuestoes())) {

			grammarDefinicao.setQuestoes(new ArrayList<GrammarQuestao>());

			for (GrammarQuestaoModel grammarQuestaoModel : grammarDefinicaoModel.getQuestoes()) {
				GrammarQuestao grammarQuestao = new GrammarQuestao(grammarQuestaoModel.getId(),
						grammarQuestaoModel.getQuestao(), null);
				grammarQuestao.setSequencia(grammarQuestaoModel.getSequencia());
				grammarDefinicao.getQuestoes().add(grammarQuestao);
			}

		}

		return grammarDefinicao;
	}

}
