angular.module('app').controller("personalDataCtrl", function($scope, $rootScope, $location, personalDataService){
	
	$scope.newrecord = angular.copy($rootScope.loggedUser);
	$scope.password = "";
	$scope.confirmeNovoPassword = "";
	$scope.newrecord.novoEmail = angular.copy($scope.newrecord.email);
	$scope.dataLoading = false;
	
	// Call the method update from personalDataService
	$scope.update = function(newrecord, confirmeNovoPassword){
		
		$scope.error = false;
		$scope.registered = false;
		
		// If the password is being updated it must be equals to the confirmation
		if(newrecord.novoPassword != null && newrecord.novoPassword != confirmeNovoPassword){
			$scope.error = "The fields 'New Password' and 'Confirm New Password' are not the same.";
		}else{
			
			newrecord.password = $scope.password;
			
			personalDataService.update(newrecord).then( 
					function successCallback(response) {
						$scope.registered = true;
						$scope.newrecord.novoPassword = "";
						$scope.confirmeNovoPassword = "";
						
					}, 
					function errorCallback(response) {
						$scope.error = response.data.status;
					}
			);
			$scope.dataLoading = false;
			
		}
		
		$scope.password = "";
	};
	
});