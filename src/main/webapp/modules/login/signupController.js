angular.module('app').controller("signupCtrl", function($scope, $location, LazyRoute, signupService){
	$scope.newrecord = {
			 nome : "",
			 telefone : "",
			 email : "",
			 tipoConta : "",
			 password : "",
			 logradouro : "",
			 bairro : "",
			 cidade : "",
			 cep : ""
			 };
	
	// Call the method signup from signupService
	$scope.signup = function(newrecord){
		
		$scope.dataLoading = true;
		$scope.error = false;
		
		signupService.signup(newrecord).then( 
			function successCallback(response) {
				$scope.registered = true;
			}, 
			function errorCallback(response) {
				 $scope.error = "The email '" + newrecord.email + "' is already registered, please choose another one.";
			}
		);
		$scope.dataLoading = false;
		
	};
});