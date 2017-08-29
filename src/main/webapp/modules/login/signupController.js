angular.module('app').controller("signupCtrl", function($scope, $location, LazyRoute, signupService){
	$scope.newrecord = {
			 nome : "",
			 sobrenome : "",
			 email : "",
			 genero : "",
			 tipoConta : "",
			 especialidade : "",
			 nomeInstituicao : "",
			 password : "",
			 confirmPassword : ""
			 };
	
	// Call the method signup from signupService
	$scope.signup = function(newrecord){
		
		$scope.dataLoading = true;
		$scope.error = false;
		
		// If the password is being updated it must be equals to the confirmation
		if(newrecord.password != newrecord.confirmPassword){
			$scope.error = "The fields 'Password' and 'Confirm password' are not the same.";
		}else{
			
			signupService.signup(newrecord).then( 
				function successCallback(response) {
					$scope.registered = true;
				}, 
				function errorCallback(response) {
					 $scope.error = "The email '" + newrecord.email + "' is already registered, please choose another one.";
				}
			);
		}
		
		$scope.dataLoading = false;
		
	};
});