angular.module("app").service("signupService", function($http, constants){
	
	// Call the signup service from SignupController class
	this.signup = function(newrecord) {	
		return $http.post(constants.baseUrl + "/signup", newrecord);
	};
});