angular.module('app').controller("loginCtrl", function($scope, $rootScope, $state, LazyRoute, $location, loginService){
	
	$scope.email = "";
	$scope.password = "";
	$scope.dataLoading = false;
	
	// Calls login method from loginService considering the username and password, if the login is done the user is redirected to main page
	$scope.login = function () {
		
		loginService.login($scope.email, $scope.password).then( 
			function successCallback(response) {
				$scope.error = null;
				$rootScope.loggedUser = response.data;
				$state.go("main");
			}, 
			function errorCallback(response) {
				$scope.error = "Invalid login. Please insert a valid email and password.";
			}
		);
		
		$scope.dataLoading = false;
    };
    
});