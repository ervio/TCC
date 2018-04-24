angular.module('app').controller("forumCtrl", function($scope, $rootScope, $state, LazyRoute, forumService){
	
	$scope.exercises = [];
	
	$scope.searchForumTopics = function(){
		forumService.getAllExercises().then( 
			function successCallback(response) {
				$(response.data).each(function(index, exercise) {
					$scope.exercises.push(angular.copy(exercise));
				});
			}
		);
	};
	
	// Method called to enter the forum
	$scope.goToForum = function(exercise){
		$rootScope.exerciseTopic = exercise;
		$state.go("forumTopic");
	};
	
	// Method called when the screen opens
	$scope.init = function(){
		$scope.searchForumTopics();
	};
	
});