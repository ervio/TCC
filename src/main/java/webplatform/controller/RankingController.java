package webplatform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webplatform.dao.ExercicioDao;
import webplatform.model.AlunoModel;
import webplatform.model.ExercicioAlunoModel;
import webplatform.model.ExercicioModel;
import webplatform.model.MusicaModel;
import webplatform.model.entity.Exercicio;
import webplatform.model.entity.ExercicioAluno;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class RankingController {

	@Autowired
	private ExercicioDao exercicioDao;

	/**
	 * Search all the resolved exercises to put in the ranking and calculates the
	 * resolution time
	 * 
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/searchAllResolvedExercises/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity searchQuestionsByExercise() {
		List<Exercicio> exercises = exercicioDao.findResolvedExercises();
		List<ExercicioModel> exercisesModel = new ArrayList<ExercicioModel>();
		for (Exercicio exercicio : exercises) {
			ExercicioModel exerciseModel = new ExercicioModel();
			exerciseModel.setNivel(exercicio.getNivel());
			exerciseModel.setNome(exercicio.getNome());
			exerciseModel.setMusica(new MusicaModel());
			exerciseModel.getMusica().setNome(exercicio.getMusica().getNome());
			exerciseModel.setExercicioAlunos(new ArrayList<ExercicioAlunoModel>());

			for (ExercicioAluno exercicioAluno : exercicio.getExercicioAlunos()) {
				if (exercicioAluno.getQuestoesCorretas() != null) {
					ExercicioAlunoModel exercicioAlunoModel = new ExercicioAlunoModel();
					exercicioAlunoModel.setAluno(new AlunoModel());
					exercicioAlunoModel.getAluno().setNome(exercicioAluno.getAluno().getNome());
					exercicioAlunoModel.setChances(exercicioAluno.getChances());
					exercicioAlunoModel.setDataInicio(exercicioAluno.getDataInicio());
					exercicioAlunoModel.setDataFim(exercicioAluno.getDataFim());
					exercicioAlunoModel.setQuestoesCorretas(exercicioAluno.getQuestoesCorretas());
					exercicioAlunoModel.setTempoResolucaoMillis(
							exercicioAlunoModel.getDataFim().getTime() - exercicioAlunoModel.getDataInicio().getTime());

					// Calculate the spent hours, minutes and seconds
					int timeInSeconds = (int) exercicioAlunoModel.getTempoResolucaoMillis() / 1000;
					int hours, minutes, seconds;
					hours = timeInSeconds / 3600;
					timeInSeconds = timeInSeconds - (hours * 3600);
					minutes = timeInSeconds / 60;
					timeInSeconds = timeInSeconds - (minutes * 60);
					seconds = timeInSeconds;

					exercicioAlunoModel.setTempoResolucaoString(hours + ":" + minutes + ":" + seconds);

					exerciseModel.getExercicioAlunos().add(exercicioAlunoModel);
				}

			}

			exercisesModel.add(exerciseModel);
		}
		return new ResponseEntity(exercisesModel, HttpStatus.OK);
	}

}
