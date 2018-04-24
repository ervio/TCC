var app = angular.module('app', ['voiceRss', 'pubnub.angular.service', 'ui.router', 'ui.bootstrap', 'youtube-embed', 'dndLists', 'ngAnimate', 'ngSanitize', 'ngFileUpload'])

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

app.config(['ttsProvider', function (ttsProvider) {
    ttsProvider.setSettings({ key: '9587c11d941e45cb879a243524ced01e' });
}]);

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
			.state('resolveExerciseVocabulary', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_vocabulary.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseLanguage', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_language.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseReading', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_reading.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseOralProduction', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_oralProduction.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseWriting', {
				url: '/WebPlatform/',
				templateUrl: 'modules/solveexercises/solveExercises_writing.html',
				controller: "solveExercisesCtrl"
			})
			.state('forumMain', {
				url: '/WebPlatform/',
				templateUrl: 'modules/forum/forumMain.html',
				controller: "forumCtrl"
			})
			.state('forumTopic', {
				url: '/WebPlatform/',
				templateUrl: 'modules/forum/forumTopic.html',
				controller: "forumCtrl"
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

app.filter('range', function() {
  return function(input, total) {
    total = parseInt(total);
    for (var i=0; i<total; i++)
      input.push(i);
    return input;
  };
});

app.filter('removeSpaces', [function() {
    return function(string) {
        if (!angular.isString(string)) {
            return string;
        }
        return string.replace(/[\s]/g, '');
    };
}]);