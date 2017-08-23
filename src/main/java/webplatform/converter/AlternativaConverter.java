package webplatform.converter;

import webplatform.model.AlternativaModel;
import webplatform.model.entity.Alternativa;

public class AlternativaConverter {

	public static final Alternativa convert(AlternativaModel alternativaModel) {
		Alternativa alternativa = new Alternativa();
		if (alternativaModel.getIdAlternativa() != 0) {
			alternativa.setIdAlternativa(alternativaModel.getIdAlternativa());
		}
		alternativa.setResposta(alternativaModel.getResposta());
		alternativa.setCorreta(alternativaModel.isCorreta());
		return alternativa;
	}

}
