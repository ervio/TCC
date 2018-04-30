angular.module('app').controller("mainCtrl", function($scope, $rootScope, mainService){
	
	$scope.dataLoading = false;
	$scope.newInvitations = "";
	$scope.acceptanceSuccess = "";
	$scope.invitations = [];
	
	$scope.rowHighilited = function(row){
      $scope.selectedRow = row;    
    }
	
	// Search for new invitations
	$scope.searchInvites = function(){
		
		mainService.searchInvites($rootScope.loggedUser.id).then( 
			function successCallback(response) {
				$(response.data).each(function(index, invite) {
					$scope.invitations.push(angular.copy(invite));
				 });
				
				if($scope.invitations.length > 0){
					$scope.newInvitations = true;
				}
				
				$scope.dataLoading = false;
			}, 
			function errorCallback(response) {
				
			}
		);
		
	};
	
	// Calls acceptInvitation method from mainService for the invitation accept
	$scope.acceptInvitation = function(teacherId, studentId){
		
		$scope.dataLoading = true;
		
		mainService.acceptInvitation(teacherId, studentId).then( 
			function successCallback(response) {
				$scope.newInvitations = "";
				$scope.acceptanceSuccess = true;
				$scope.invitations = [];
				$scope.dataLoading = false;
			}, 
			function errorCallback(response) {
				
			}
		);
	};
	
	// Initial method called when the main screen opens for a student
	$scope.init = function(){
		
		$scope.dataLoading = true;
		
		if($rootScope.loggedUser.tipoConta == 'Student'){
			$scope.searchInvites();
		}
	};
	
});