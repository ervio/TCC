angular.module('app').controller("loginCtrl", function($scope, $rootScope, $state, LazyRoute, $location, loginService, $resource){
	
	$scope.dataLoading = false;
	$scope.email = "";
	$scope.password = "";
	
	// Calls login method from loginService considering the username and password, if the login is done the user is redirected to main page
	$scope.login = function () {
		
		$scope.dataLoading = true;
		
		loginService.login($scope.email, $scope.password).then( 
			function successCallback(response) {
				$scope.error = null;
				$rootScope.loggedUser = response.data;
				$scope.dataLoading = false;
				$state.go("main");
			}, 
			function errorCallback(response) {
				$scope.dataLoading = false;
				$scope.error = "Invalid login. Please insert a valid email and password.";
			}
		);
		
    };
    
});