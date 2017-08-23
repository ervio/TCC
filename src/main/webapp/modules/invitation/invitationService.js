angular.module("app").service("invitationService", function($http, constants){
	
	// Call searchStudents service from InvitationController class
	this.searchStudents = function(name, email, teacherId){
		if(name == null || name == ""){
			name = " ";
		}
		if(email == null || email == ""){
			email = " ";
		}
		return $http.get(constants.baseUrl + "/searchStudents/" + name + "/" + email + "/" + teacherId);
	}
	
	// Call sendInvites service from InvitationController class
	this.sendInvites = function(studentsIds, teacherId){
		return $http.get(constants.baseUrl + "/sendInvites/" + studentsIds + "/" + teacherId);
	}
	
});