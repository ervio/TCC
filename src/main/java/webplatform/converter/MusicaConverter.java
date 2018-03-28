package webplatform.converter;

import webplatform.model.MusicaModel;
import webplatform.model.entity.Musica;

public class MusicaConverter {

	public static final Musica convert(MusicaModel musicaModel) {
		Musica musica = new Musica();
		if (musicaModel.getIdMusica() != 0) {
			musica.setIdMusica(musicaModel.getIdMusica());
		}
		musica.setNome(musicaModel.getNome());
		musica.setCantor(musicaModel.getCantor());
		musica.setLink(musicaModel.getLink());
		musica.setLetra(musicaModel.getLetra());
		musica.setLetraOrdenar(musicaModel.getLetraOrdenar());
		return musica;
	}
}
