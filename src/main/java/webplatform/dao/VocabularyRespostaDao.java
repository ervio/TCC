package webplatform.dao;

import java.util.List;

import webplatform.model.entity.Imagem;
import webplatform.model.entity.VocabularyResposta;

public interface VocabularyRespostaDao {

	public VocabularyResposta saveOrUpdate(VocabularyResposta entity);

	public List<VocabularyResposta> findByImageList(List<Imagem> imagens);

	public void deleteAll(List<VocabularyResposta> vocabularyRespostas);
}
