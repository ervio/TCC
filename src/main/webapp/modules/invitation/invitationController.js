angular.module('app').controller("invitationCtrl", function($scope, $rootScope, invitationService){
	
	$scope.allSelected = false;
	$scope.name = "";
	$scope.email = "";
	$scope.students = [];
	$scope.error = "";
	$scope.invitationSuccess = "";
	
	// The method highlights the selected rows from the table
	$scope.rowHighilited = function(row){
      $scope.selectedRow = row;    
    }
	
	// Call the method searchStudents from invitationService
	$scope.searchStudents = function(){
		
		$scope.dataLoading = true;
		$scope.error = "";
		$scope.invitationSuccess = "";
		
		$scope.students = [];
		
		invitationService.searchStudents($scope.name, $scope.email).then( 
				function successCallback(response) {
					$(response.data).each(function(index, student) {
						student.selected;
						$scope.students.push(angular.copy(student));
					 });
					
					$scope.dataLoading = false;
				}, 
				function errorCallback(response) {
					$scope.dataLoading = false;
				}
		);
		
	};
	
	// Method called when the screen opens
	$scope.init = function(){
		$scope.searchStudents();
	};
    
	// Method selects all students from the table
	$scope.selectAll = function(){
		
		if($scope.allSelected){
			
			$($scope.students).each(function(index, student) {
				student.selected = true;
			 });
			
		}else{
			
			$($scope.students).each(function(index, student) {
				student.selected = false;
			 });
			
		}
		
	};
	
	// The method deselect all students from the table
	$scope.deselectHeader = function(){
		$scope.allSelected = false;
	};
	
	// Call the method sendInvites from invitationService
	$scope.sendInvite = function(){
		$scope.dataLoading = true;
		var selected = false;
		$scope.error = "";
		$scope.invitationSuccess = "";
		$scope.studentsIds = [];
		
		$($scope.students).each(function(index, student) {
			if(student.selected){
				$scope.studentsIds.push(student.id);
				selected = true;
			}
		 });
		
		if(!selected){
			$scope.error = "There isn't any student selected.";
			$scope.dataLoading = false;
		}else{
			
			invitationService.sendInvites($scope.studentsIds, $rootScope.loggedUser.id).then( 
					function successCallback(response) {
						$scope.invitationSuccess = true;
						$scope.dataLoading = false;
					}, 
					function errorCallback(response) {
						$scope.dataLoading = false;
					}
			);
			
		}
	};
});