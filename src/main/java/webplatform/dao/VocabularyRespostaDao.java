package webplatform.dao;

import java.util.List;

import webplatform.model.entity.ExercicioAluno;
import webplatform.model.entity.Imagem;
import webplatform.model.entity.VocabularyResposta;

public interface VocabularyRespostaDao {

	public VocabularyResposta saveOrUpdate(VocabularyResposta entity);

	public List<VocabularyResposta> findByImageList(List<Imagem> imagens);

	public List<VocabularyResposta> findByExercicioAlunoList(List<ExercicioAluno> exercicioAlunoList);

	public void deleteAll(List<VocabularyResposta> vocabularyRespostas);
}
