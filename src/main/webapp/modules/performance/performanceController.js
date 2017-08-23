angular.module('app').controller("performanceCtrl", function($scope, $rootScope, performanceService){
	
	$scope.studentName = "";
	$scope.studentEmail = "";
	$scope.exerciseName = "";
	$scope.exerciseLevel = "";
	$scope.basicList = [];
	$scope.intermediateList = [];
	$scope.advancedList = [];
	
	// Call the method searchResolvedExercises from performanceService
	$scope.searchResolvedExercises = function(){
		
		$scope.basicList = [];
		$scope.intermediateList = [];
		$scope.advancedList = [];
		var studentId = "";
		var teacherId = "";
		
		// If the logged user is a teacher all the exercises related to the students will be searched, otherwise only the exercises related to the logged student
		if($rootScope.loggedUser.tipoConta != 'Teacher'){
			studentId = $rootScope.loggedUser.id;
		}else{
			teacherId = $rootScope.loggedUser.id;
		}
		
		performanceService.searchResolvedExercises($scope.studentName, $scope.studentEmail, $scope.exerciseName, $scope.exerciseLevel, studentId, teacherId).then( 
			function successCallback(response) {
				$(response.data).each(function(index, exercise) {
					
					// The exercises will be separated in levels
					if(exercise.exercicio.nivel == 'Basic'){
						$scope.basicList.push(angular.copy(exercise));
					}
					
					if(exercise.exercicio.nivel == 'Intermediate'){
						$scope.intermediateList.push(angular.copy(exercise));
					}
					
					if(exercise.exercicio.nivel == 'Advanced'){
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
	
	// Method to highlight the selected rows from the tables
	$scope.rowHighilited = function(row, list){
		if(list == 'Basic'){
			$scope.basicSelectedRow = row; 
		}
		if(list == 'Intermediate'){
			$scope.intermediateSelectedRow = row; 
		}
		if(list == 'Advanced'){
			$scope.advancedSelectedRow = row; 
		}
    }
	
});