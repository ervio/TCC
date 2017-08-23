angular.module("app").service("mainService", function($http, constants){
	
	// Calls the searchInvites service from MainController class
	this.searchInvites = function(studentId){
		return $http.get(constants.baseUrl + "/searchInvites/" + studentId);
	}
	
	// Calls the acceptInvitation service from MainController class
	this.acceptInvitation = function(teacherId, studentId){
		return $http.get(constants.baseUrl + "/acceptInvitation/" + teacherId + "/" + studentId);
	}
});