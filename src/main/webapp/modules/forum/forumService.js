angular.module("app").service("forumService", function($http, constants){
	
	// Call the getAllExercises service from ForumController class
	this.getAllExercises = function() {	
		return $http.get(constants.baseUrl + "/getAllExercises");
	};
	
	// Call the getForumPosts service from ForumController class
	this.getForumPosts = function(exerciseId){
		return $http.get(constants.baseUrl + "/getForumPosts/" + exerciseId);
	};
	
	// Call the replyPost service from ForumController class
	this.replyPost = function(forumPost){
		return $http.post(constants.baseUrl + "/replyPost", forumPost);
	};
});