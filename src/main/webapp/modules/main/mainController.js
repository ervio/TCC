angular.module('app').controller("mainCtrl", function($scope, $rootScope, mainService){
	
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
			}, 
			function errorCallback(response) {
				
			}
		);
		
	};
	
	// Calls acceptInvitation method from mainService for the invitation accept
	$scope.acceptInvitation = function(teacherId, studentId){
		mainService.acceptInvitation(teacherId, studentId).then( 
			function successCallback(response) {
				$scope.newInvitations = "";
				$scope.acceptanceSuccess = true;
				$scope.invitations = [];
			}, 
			function errorCallback(response) {
				
			}
		);
	};
	
	// Initial method called when the main screen opens for a student
	$scope.init = function(){
		if($rootScope.loggedUser.tipoConta == 'Student'){
			$scope.searchInvites();
		}
	};
	
});