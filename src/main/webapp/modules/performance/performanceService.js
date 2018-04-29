angular.module("app").service("performanceService", function($http, constants){
	
	// Call the searchResolvedExercises service from PerformanceController class
	this.searchResolvedExercises = function(studentName, studentEmail, songName, level, studentId, teacherId){
		if(studentName == null || studentName == ""){
			studentName = " ";
		}
		if(studentEmail == null || studentEmail == ""){
			studentEmail = " ";
		}
		if(songName == null || songName == ""){
			songName = " ";
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
		return $http.get(constants.baseUrl + "/searchResolvedExercises/" + studentName + "/" + studentEmail + "/" + songName + "/" + level + "/" + studentId + "/" + teacherId + "/");
	}
	
});