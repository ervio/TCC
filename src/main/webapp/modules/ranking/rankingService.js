angular.module("app").service("rankingService", function($http, constants){
	
	// Call the searchAllResolvedExercises service from RankingController class
	this.searchAllResolvedExercises = function(){
		return $http.get(constants.baseUrl + "/searchAllResolvedExercises/");
	}
	
});