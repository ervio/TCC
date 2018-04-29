package webplatform.dao;

import java.util.List;

import webplatform.model.entity.GrammarDefinicao;
import webplatform.model.entity.GrammarQuestao;
import webplatform.model.entity.GrammarResposta;

public interface GrammarRespostaDao {

	public GrammarResposta saveOrUpdate(GrammarResposta entity);

	public List<GrammarResposta> findByGrammarQuestaoList(List<GrammarQuestao> grammarQuestoes);

	public List<GrammarResposta> findByGrammarDefinicaoList(List<GrammarDefinicao> grammarDefinicoes);

	public void deleteAll(List<GrammarResposta> grammarRespostas);
}
