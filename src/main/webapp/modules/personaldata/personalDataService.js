angular.module("app").service("personalDataService", function($http, constants){
	
	// Call updateUserData service from PersonalDataController class
	this.update = function(newrecord) {	
		return $http.post(constants.baseUrl + "/updateUserData", newrecord);
	};
});