var app = angular.module('app', ['voiceRss', 'pubnub.angular.service', 'ui.router', 'ui.bootstrap', 'youtube-embed', 'dndLists', 'ngAnimate', 'ngSanitize', 'ngFileUpload', 'ngResource'])

.config(function($stateProvider, $urlRouterProvider, $locationProvider, $injector) {
	
	$urlRouterProvider.otherwise('/index');
	
	$stateProvider
	
	.state('index', {
		url: '/index',
		templateUrl: 'modules/login/login.html',
		controller: "loginCtrl"
	})
	
	.state('personalData', {
		url: '/personalData',
		templateUrl: 'modules/personaldata/personalData.html',
		controller: "personalDataCtrl"
	})
	.state('exercisesManagement', {
		url: '/exercisesManagement',
		templateUrl: 'modules/exercisesmanagement/exercisesManagement.html',
		controller: "exercisesMgmtCtrl"
	})
	.state('invitation', {
		url: '/invitation',
		templateUrl: 'modules/invitation/invitation.html',
		controller: "invitationCtrl"
	})
	.state('solveExercises', {
		url: '/solveExercises',
		templateUrl: 'modules/solveexercises/solveExercises.html',
		controller: "solveExercisesCtrl"
	})
	.state('performance', {
		url: '/performance',
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
	    		url: '/signup',
	    		templateUrl: 'modules/login/signup.html',
	    		controller: "signupCtrl"
	    	})
	        .state('main', {
	    		url: '/main',
	    		templateUrl: 'modules/main/main.html',
	    		controller: "mainCtrl"
	    	})
	        .state('newExercise', {
	    		url: '/exercisesManagementNewExercise',
	    		templateUrl: 'modules/exercisesmanagement/exercisesManagementNewExercise.html',
	    		controller: "exercisesMgmtCtrl"
	    	})
	    	
	    	.state('resolveExerciseLyrics', {
				url: '/solveExercises_song',
				templateUrl: 'modules/solveexercises/solveExercises_song.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseQuestions', {
				url: '/solveExercises_questions',
				templateUrl: 'modules/solveexercises/solveExercises_questions.html',
				controller: "solveExercisesCtrl"
			})
			.state('ranking', {
				url: '/ranking',
				templateUrl: 'modules/ranking/ranking.html',
				controller: "rankingCtrl"
			})
			.state('logout', {
				url: '/login',
				templateUrl: 'modules/login/login.html',
				controller: "rankingCtrl"
			})
			.state('resolveExerciseVocabulary', {
				url: '/solveExercises_vocabulary',
				templateUrl: 'modules/solveexercises/solveExercises_vocabulary.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseLanguage', {
				url: '/solveExercises_grammar',
				templateUrl: 'modules/solveexercises/solveExercises_language.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseReading', {
				url: '/solveExercises_reading',
				templateUrl: 'modules/solveexercises/solveExercises_reading.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseOralProduction', {
				url: '/solveExercises_oralProduction',
				templateUrl: 'modules/solveexercises/solveExercises_oralProduction.html',
				controller: "solveExercisesCtrl"
			})
			.state('resolveExerciseWriting', {
				url: '/solveExercises_writing',
				templateUrl: 'modules/solveexercises/solveExercises_writing.html',
				controller: "solveExercisesCtrl"
			})
			.state('forumMain', {
				url: '/forumMain',
				templateUrl: 'modules/forum/forumMain.html',
				controller: "forumCtrl"
			})
			.state('forumTopic', {
				url: '/forumTopic',
				templateUrl: 'modules/forum/forumTopic.html',
				controller: "forumCtrl"
			})
	    	;
        
        $log.log("Sub View1 state configured.");
    });
    
    return {};
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