angular.module("app").service("solveExercisesService", function($http, constants){
	
	// Call the searchExercisesNotResolved service from SolveExercisesController class
	this.searchExercises = function(studentId) {	
		return $http.get(constants.baseUrl + "/searchExercisesNotResolved/" + studentId);
	};
	
	// Call the updateExercise service from SolveExercisesController class
	this.updateExercise = function(exercise){
		return $http.post(constants.baseUrl + "/updateExercise", exercise);
	};
	
	// Call the searchQuestions service from SolveExercisesController class
	this.searchQuestions = function(exerciseId){
		return $http.get(constants.baseUrl + "/searchQuestions/" + exerciseId);
	};
	
	// Call the searchAlternativesByQuestion service from SolveExercisesController class
	this.searchAlternatives = function(questionId){
		return $http.get(constants.baseUrl + "/searchAlternativesByQuestion/" + questionId);
	};
	
	// Call the submitQuestions service from SolveExercisesController class
	this.submitQuestions = function(exercise){
		return $http.post(constants.baseUrl + "/submitQuestions", exercise);
	};
});