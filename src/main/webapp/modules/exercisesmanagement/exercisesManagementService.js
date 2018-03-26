angular.module("app").service("exercisesManagementService", function($http, constants){
	
	// Calls the saveExercise service from ExercisesManagementController class
	this.saveExercise = function(exercicio) {	
		return $http.post(constants.baseUrl + "/saveExercise", exercicio);
	};
	
	// Calls the searchAllExercises service from ExercisesManagementController class
	this.searchAll = function(professorId){
		return $http.get(constants.baseUrl + "/searchAllExercises/" + professorId);
	}
	
	// Calls the searchQuestionsByExercise service from ExercisesManagementController class
	this.searchQuestionsByExercise = function(exercicioId){
		return $http.get(constants.baseUrl + "/searchQuestionsByExercise/" + exercicioId);
	}
	
	// Calls the searchOptionsByQuestion service from ExercisesManagementController class
	this.searchOptionsByQuestion = function(questionId){
		return $http.get(constants.baseUrl + "/searchOptionsByQuestion/" + questionId);
	}
	
	// Calls the deleteAlternatives service from ExercisesManagementController class
	this.deleteAlternatives = function(alternativesToDelete){
		return $http.get(constants.baseUrl + "/deleteAlternatives/" + alternativesToDelete);
	}
	
	// Calls the deleteQuestions service from ExercisesManagementController class
	this.deleteQuestions = function(questionsToDelete){
		return $http.get(constants.baseUrl + "/deleteQuestions/" + questionsToDelete);
	}
	
	// Calls the deleteExercise service from ExercisesManagementController class
	this.deleteExercise = function(exerciseId){
		return $http.get(constants.baseUrl + "/deleteExercise/" + exerciseId);
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