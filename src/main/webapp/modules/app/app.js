var app = angular.module('app', ['ui.router', 'ui.bootstrap', 'youtube-embed', 'dndLists', 'ngAnimate', 'ngSanitize'])

.config(function($stateProvider, $urlRouterProvider, $locationProvider, $injector) {
	
	$stateProvider
	
	.state('index', {
		url: '/WebPlatform/',
		templateUrl: 'modules/login/login.html',
		controller: "loginCtrl"
	})
	
	.state('personalData', {
		url: '/WebPlatform/',
		templateUrl: 'modules/personaldata/personalData.html',
		controller: "personalDataCtrl"
	})
	.state('exercisesManagement', {
		url: '/WebPlatform/',
		templateUrl: 'modules/exercisesmanagement/exercisesManagement.html',
		controller: "exercisesMgmtCtrl"
	})
	.state('invitation', {
		url: '/WebPlatform/',
		templateUrl: 'modules/invitation/invitation.html',
		controller: "invitationCtrl"
	})
	.state('solveExercises', {
		url: '/WebPlatform/',
		templateUrl: 'modules/solveexercises/solveExercises.html',
		controller: "solveExercisesCtrl"
	})
	.state('performance', {
		url: '/WebPlatform/',
		templateUrl: 'modules/performance/performance.html',
		controller: "performanceCtrl"
	});
	
	$locationProvider.html5Mode({
	  enabled: true,
	  requireBase: false
	});

	// Cache injector
    config_injector = $injector;
});

app.factory("LazyRoute", function ($log) {
    
    config_injector.invoke(function ($stateProvider) {
        $stateProvider
        	
	        .state('signup', {
	    		url: '/WebPlatform/',
	    		templateUrl: 'modules/login/signup.html',
	    		controller: "signupCtrl"
	    	})
	        .state('main', {
	    		url: '/WebPlatform/',
	    		templateUrl: 'modules/main/main.html',
	    		controller: "mainCtrl"
	    	})
	        .state('newExercise', {
	    		url: '/WebPlatform/',
	    		templateUrl: 'modules/exercisesmanagement/exercisesManagementNewExercise.html',
	    		controller: "exercisesMgmtCtrl"
	    	})
	    	
	    	.state('resolveExerciseLyrics', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_song.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseQuestions', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_questions.html',
				controller: "solveExercisesCtrl"
			})
			.state('ranking', {
				url: '/WebPlatform/',
				templateUrl: 'modules/ranking/ranking.html',
				controller: "rankingCtrl"
			})
			.state('logout', {
				url: '/WebPlatform/',
				templateUrl: 'modules/login/login.html',
				controller: "rankingCtrl"
			})
	    	;
        
        $log.log("Sub View1 state configured.");
    });
    
    return {};
});

app.directive('webPlatformLoading', function() {
	return {
		template: 
			'<div class="text-center">' +
				'<img src="img/loading-animation-square.gif">' +
			'</div>'
	}
});