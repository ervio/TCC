angular.module("app").service("loginService", function($http, constants){
	
	// Calls the login service from LoginController class
	this.login = function(email, password) {	
		return $http.get(constants.baseUrl + "/login/" + email + "/" + password);
	};
});