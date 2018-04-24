angular.module("app").service("forumService", function($http, constants){
	
	// Call the getAllExercises service from ForumController class
	this.getAllExercises = function() {	
		return $http.get(constants.baseUrl + "/getAllExercises");
	};
	
});