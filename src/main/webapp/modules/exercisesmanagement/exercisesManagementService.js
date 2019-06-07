angular.module("app").service("exercisesManagementService", function($http, constants){
	
	// Calls the saveExercise service from ExercisesManagementController class
	this.saveExercise = function(exercicio) {	
		return $http.post(constants.baseUrl + "/saveExercise", exercicio);
	};
	
	// Calls the searchAllExercises service from ExercisesManagementController class
	this.searchAll = function(professorId){
		return $http.get(constants.baseUrl + "/searchAllExercises/" + professorId);
	}
	
	// Calls the searchGrammarDefinitionsByExercise service from ExercisesManagementController class
	this.searchGrammarDefinitionsByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchGrammarDefinitionsByExercise/" + exercicioId);
	}
	
	// Calls the searchGrammarAlternativesByExercise service from ExercisesManagementController class
	this.searchGrammarAlternativesByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchGrammarAlternativesByExercise/" + exercicioId);
	}
	
	// Calls the searchReadingQuestionsByExercise service from ExercisesManagementController class
	this.searchReadingQuestionsByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchReadingQuestionsByExercise/" + exercicioId);
	}
	
	// Calls the searchReadingAlternativesByExercise service from ExercisesManagementController class
	this.searchReadingAlternativesByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchReadingAlternativesByExercise/" + exercicioId);
	}
	
	// Calls the searchPronunciationQuestionsByExercise service from ExercisesManagementController class
	this.searchPronunciationQuestionsByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchPronunciationQuestionsByExercise/" + exercicioId);
	}
	
	// Calls the searchPronunciationQuestionsPartsByExercise service from ExercisesManagementController class
	this.searchPronunciationQuestionsPartsByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchPronunciationQuestionsPartsByExercise/" + exercicioId);
	}
	
	// Calls the searchPicturesByExercise service from ExercisesManagementController class
	this.searchPicturesByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchPicturesByExercise/" + exercicioId);
	}
	
	// Calls the deletePictures service from ExercisesManagementController class
	this.deletePictures = function(picturesToDelete){
		return $http.get(constants.baseUrl + "/deletePictures/" + picturesToDelete);
	}

	// Calls the deleteGrammarAlternativesAndQuestions service from ExercisesManagementController class
	this.deleteGrammarAlternativesAndQuestions = function(grammarAlternativesToDelete, grammarDefinitionsToDelete){
		
		if(grammarAlternativesToDelete.length > 0 && grammarDefinitionsToDelete.length > 0){
			return $http.get(constants.baseUrl + "/deleteGrammarAlternativesAndQuestions/" + grammarAlternativesToDelete + "/" + grammarDefinitionsToDelete);
		}
		else if(grammarAlternativesToDelete.length > 0 && grammarDefinitionsToDelete.length == 0){
			return $http.get(constants.baseUrl + "/deleteGrammarAlternatives/" + grammarAlternativesToDelete);
		}
		else{
			return $http.get(constants.baseUrl + "/deleteGrammarDefinitions/" + grammarDefinitionsToDelete);
		}
	}
	
	// Calls the deleteReadingAlternativesAndQuestions service from ExercisesManagementController class
	this.deleteReadingAlternativesAndQuestions = function(readingAlternativesToDelete, readingQuestionsToDelete){
		
		if(readingAlternativesToDelete.length > 0 && readingQuestionsToDelete.length > 0){
			return $http.get(constants.baseUrl + "/deleteReadingAlternativesAndQuestions/" + readingAlternativesToDelete + "/" + readingQuestionsToDelete);
		}
		else if(readingAlternativesToDelete.length > 0 && readingQuestionsToDelete.length == 0){
			return $http.get(constants.baseUrl + "/deleteReadingAlternatives/" + readingAlternativesToDelete);
		}
		else{
			return $http.get(constants.baseUrl + "/deleteReadingQuestions/" + readingQuestionsToDelete);
		}
	}
	
	// Calls the deletePronunciationPartsAndQuestions service from ExercisesManagementController class
	this.deletePronunciationPartsAndQuestions = function(pronunciationQuestionPartToDelete, pronunciationQuestionToDelete){
		
		if(pronunciationQuestionPartToDelete.length > 0 && pronunciationQuestionToDelete.length > 0){
			return $http.get(constants.baseUrl + "/deletePronunciationPartsAndQuestions/" + pronunciationQuestionPartToDelete + "/" + pronunciationQuestionToDelete);
		}
		else if(pronunciationQuestionPartToDelete.length > 0 && pronunciationQuestionToDelete.length == 0){
			return $http.get(constants.baseUrl + "/deletePronunciationQuestionParts/" + pronunciationQuestionPartToDelete);
		}
		else{
			return $http.get(constants.baseUrl + "/deletePronunciationQuestions/" + pronunciationQuestionToDelete);
		}
	}
	
	// Calls the deleteExercise service from ExercisesManagementController class
	this.deleteExercise = function(exerciseId){
		return $http.get(constants.baseUrl + "/deleteExercise/" + exerciseId);
	}
	
	// Calls the searchUnresolvedExercises service from ExercisesManagementController class
	this.searchUnresolvedExercises = function(exerciseId){
		return $http.get(constants.baseUrl + "/searchUnresolvedExercises/" + exerciseId);
	}
	
	// Calls the searchStudentsToAssign service from ExercisesManagementController class
	this.searchStudents = function(name, email, teacherId){
		if(name == null || name == ""){
			name = " ";
		}
		if(email == null || email == ""){
			email = " ";
		}
		return $http.get(constants.baseUrl + "/searchStudentsToAssign/" + name + "/" + email + "/" + teacherId);
	}
	
	// Calls the assignExercise service from ExercisesManagementController class
	this.assignExercise = function(studentsIds, idExercicio){
		return $http.get(constants.baseUrl + "/assignExercise/" + studentsIds + "/" + idExercicio);
	}
	
	
	this.test = function(pictures, exercicio){
		
		var URL = constants.baseUrl + "/test";
	    var fd = new FormData();
	    fd.append('file', pictures[0]);
	    fd.append('exercicio', angular.toJson(exercicio, true));
	    return $http.post(URL, fd, {
	        transformRequest : angular.identity,
	        headers : {
	            'Content-Type' : undefined
	        }
	    });
	}
});