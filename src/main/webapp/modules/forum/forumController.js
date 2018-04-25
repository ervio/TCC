angular.module('app').controller("forumCtrl", function($scope, $rootScope, $state, LazyRoute, forumService){
	
	$scope.exercises = [];
	$scope.posts = [];
	$scope.postTemp = {
		texto : ""
	}; 
	
	$scope.searchForumTopics = function(){ 
		forumService.getAllExercises().then( 
			function successCallback(response) {
				$(response.data).each(function(index, exercise) {
					$scope.exercises.push(angular.copy(exercise));
				});
			}
		);
	};
	
	$scope.searchForumPosts = function(){
		forumService.getForumPosts($rootScope.exerciseTopic.idExercicio).then( 
			function successCallback(response) {
				$(response.data).each(function(index, post) {
					$scope.posts.push(angular.copy(post));
				});
			}
		);
	};
	
	$scope.replyPost = function(){
		forumService.replyPost($scope.postTemp).then( 
			function successCallback(response) {
				$scope.posts = [];
				
				$(response.data).each(function(index, post) {
					$scope.posts.push(angular.copy(post));
				});
				
				$scope.postTemp.texto = "";
				delete $scope.postTemp.postRespondido;
			}
		);
	};
	
	$scope.replyAnotherPost = function(post){
		$scope.postTemp.postRespondido = angular.copy(post);
	};
	
	// Method called to enter the forum
	$scope.goToForum = function(exercise){
		$rootScope.exerciseTopic = exercise;
		$state.go("forumTopic");
	};
	
	// Method called when the topic screen opens
	$scope.initTopic = function(){
		$scope.searchForumPosts();
		$scope.postTemp.exercicio = {
			idExercicio : $rootScope.exerciseTopic.idExercicio
		};
		if($rootScope.loggedUser.tipoConta == 'Teacher'){
			$scope.postTemp.professor = {
				id : $rootScope.loggedUser.id
			};
		}else{
			$scope.postTemp.aluno = {
				id : $rootScope.loggedUser.id
			};
		}
	};
	
	// Method called when the main forum screen opens
	$scope.init = function(){
		delete $rootScope.exerciseTopic;
		$scope.searchForumTopics();
	};
	
});