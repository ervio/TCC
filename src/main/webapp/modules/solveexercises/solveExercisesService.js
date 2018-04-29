angular.module("app").service("solveExercisesService", function($http, constants){
	
	// Call the searchAssignedExercises service from SolveExercisesController class
	this.searchAssignedExercises = function(studentId) {	
		return $http.get(constants.baseUrl + "/searchAssignedExercises/" + studentId);
	};
	
	
	// Call the searchAllNotAssignedExercises service from SolveExercisesController class
	this.searchAllNotAssignedExercises = function(studentId) {	
		return $http.get(constants.baseUrl + "/searchAllNotAssignedExercises/" + studentId);
	};
	
	// Call the updateExercise service from SolveExercisesController class
	this.updateExercise = function(exercise){
		return $http.post(constants.baseUrl + "/updateExercise", exercise);
	};
	
	this.searchVocabularyPictures = function(exerciseId){
		return $http.get(constants.baseUrl + "/searchVocabularyPictures/" + exerciseId);
	};
	
	// Call the searchGrammarQuestions service from SolveExercisesController class
	this.searchGrammarQuestions = function(exerciseId){
		return $http.get(constants.baseUrl + "/searchGrammarQuestions/" + exerciseId);
	};
	
	// Call the searchReadingQuestions service from SolveExercisesController class
	this.searchReadingQuestions = function(exerciseId){
		return $http.get(constants.baseUrl + "/searchReadingQuestions/" + exerciseId);
	};
	
	// Call the searchReadingAlternatives service from SolveExercisesController class
	this.searchReadingAlternatives = function(questionId){
		return $http.get(constants.baseUrl + "/searchReadingAlternatives/" + questionId);
	};
	
	// Call the searchPronunciationQuestions service from SolveExercisesController class
	this.searchPronunciationQuestions = function(exerciseId){
		return $http.get(constants.baseUrl + "/searchPronunciationQuestions/" + exerciseId);
	};
	
	// Call the searchPronunciationParts service from SolveExercisesController class
	this.searchPronunciationParts = function(questionId){
		return $http.get(constants.baseUrl + "/searchPronunciationParts/" + questionId);
	};
	
	// Call the submitExercise service from SolveExercisesController class
	this.submitExercise = function(exercise){
		return $http.post(constants.baseUrl + "/submitExercise", exercise);
	};
	
});