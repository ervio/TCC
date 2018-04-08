package webplatform.model;

import java.util.HashSet;
import java.util.Set;

public class PronunciationQuestaoModel {

	private Long id;
	private Set<PronunciationQuestaoParteModel> pronunciationQuestaoPartes = new HashSet<PronunciationQuestaoParteModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<PronunciationQuestaoParteModel> getPronunciationQuestaoPartes() {
		return pronunciationQuestaoPartes;
	}

	public void setPronunciationQuestaoPartes(Set<PronunciationQuestaoParteModel> pronunciationQuestaoPartes) {
		this.pronunciationQuestaoPartes = pronunciationQuestaoPartes;
	}

}
