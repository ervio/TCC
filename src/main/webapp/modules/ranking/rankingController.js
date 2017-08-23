angular.module('app').controller("rankingCtrl", function($scope, $rootScope, rankingService){
	
	$scope.basicList = [];
	$scope.intermediateList = [];
	$scope.advancedList = [];
	
	// Call the method searchAllResolvedExercises from rankingService
    $scope.searchResolvedExercises = function(){
		
		$scope.basicList = [];
		$scope.intermediateList = [];
		$scope.advancedList = [];
		
		rankingService.searchAllResolvedExercises().then( 
			function successCallback(response) {
				$(response.data).each(function(indexExercise, exercise) {
					
					exercise.resolvedExercises = [];
						
					if(exercise.nivel == 'Basic'){
						$scope.basicList.push(angular.copy(exercise));
					}
					
					if(exercise.nivel == 'Intermediate'){
						$scope.intermediateList.push(angular.copy(exercise));
					}
					
					if(exercise.nivel == 'Advanced'){
						$scope.advancedList.push(angular.copy(exercise));
					}
					
				});
				
			}, 
			function errorCallback(response) {
				
			}
		);
		
	};
	
	// Method called when the screen opens
	$scope.init = function(){
		$scope.searchResolvedExercises();
	};
	
});