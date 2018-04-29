package webplatform.dao;

import java.util.List;

import webplatform.model.entity.PronunciationQuestaoParte;
import webplatform.model.entity.PronunciationResposta;

public interface PronunciationRespostaDao {

	public PronunciationResposta saveOrUpdate(PronunciationResposta entity);

	public List<PronunciationResposta> findByPronunciationQuestaoParteList(
			List<PronunciationQuestaoParte> pronunciationQuestaoPartes);

	public void deleteAll(List<PronunciationResposta> pronunciationRespostas);
}
