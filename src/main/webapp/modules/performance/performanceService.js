angular.module("app").service("performanceService", function($http, constants){
	
	// Call the searchResolvedExercises service from PerformanceController class
	this.searchResolvedExercises = function(studentName, studentEmail, exerciseName, level, studentId, teacherId){
		if(studentName == null || studentName == ""){
			studentName = " ";
		}
		if(studentEmail == null || studentEmail == ""){
			studentEmail = " ";
		}
		if(exerciseName == null || exerciseName == ""){
			exerciseName = " ";
		}
		if(level == null || level == ""){
			level = " ";
		}
		if(studentId == null || studentId == ""){
			studentId = " ";
		}
		if(teacherId == null || teacherId == ""){
			teacherId = " ";
		}
		return $http.get(constants.baseUrl + "/searchResolvedExercises/" + studentName + "/" + studentEmail + "/" + exerciseName + "/" + level + "/" + studentId + "/" + teacherId + "/");
	}
	
});