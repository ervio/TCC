angular.module('app').controller("solveExercisesCtrl", function($scope, $rootScope, $state, Pubnub, LazyRoute, solveExercisesService, tts){
	
	$scope.exercises = [];
	$scope.exercise;
	$scope.splittedLyrics = [];
	$scope.error;
	$scope.questionsSubmitted = false;
	$scope.speaking = false;
	
	// TODO: Remover verificação de questões
	if(!$rootScope.questions){
		$rootScope.questions = [];
	}
	
	if(!$rootScope.models){
		$rootScope.models = {
	        selected: null,
	        lists: {"A": [], "B": []}
	    };
	}
	
	if(!$rootScope.vocabularyPictures){
		$rootScope.vocabularyPictures = [];
	}
	
	if(!$rootScope.grammarQuestions){
		$rootScope.grammarQuestions = [];
	}
	
	if(!$rootScope.grammarDefinitions){
		$rootScope.grammarDefinitions = [];
	}
	
	if(!$rootScope.readingQuestions){
		$rootScope.readingQuestions = [];
	}
	
	if(!$rootScope.pronunciationQuestions){
		$rootScope.pronunciationQuestions = [];
	}
	
	// Method called when the main screen with exercises to be resolved is open. It calls the method searchExercises from solveExercisesService
	$scope.init = function(){
		
		delete $rootScope.models;
		delete $rootScope.questions;
		delete $rootScope.vocabularyPictures;
		delete $rootScope.grammarQuestions;
		delete $rootScope.grammarDefinitions;
		delete $rootScope.readingQuestions;
		delete $rootScope.pronunciationQuestions;
		
		solveExercisesService.searchExercises($rootScope.loggedUser.id).then( 
			function successCallback(response) {
				
				$(response.data).each(function(index, exercise) {
					$scope.exercises.push(angular.copy(exercise));
				});
				
			}, 
			function errorCallback(response) {
				
			}
		);
	};
	
	// Method called when the youtube video screen is open
	$scope.initSong = function(){
		$scope.splittedLyrics = $rootScope.exerciseToEdit.exercicio.musica.letraOrdenar.split("\n");
		if($rootScope.models.lists.A.length == 0 && $rootScope.models.lists.B.length == 0){
			$($scope.splittedLyrics).each(function(index, sentence) {
				$rootScope.models.lists.A.push({label: sentence});
			});
			
			// The lyrics are sorted in alphabetical order
			$rootScope.models.lists.A.sort(function(a, b) {
				 return b.label < a.label ?  1 // if b should come earlier, push a to end
				         : b.label > a.label ? -1 // if b should come later, push a to begin
				         : 0;                   // a and b are equal
			});
			
			if(!$rootScope.exerciseToEdit.dataInicio){
				$rootScope.exerciseToEdit.dataInicio = new Date();
				$rootScope.exerciseToEdit.chances = 0;
				$scope.updateExercise();
			}
		}
	};
	
	// TODO: Remover este método
	// Method called when the questions screen is open. It calls the method searchAlternatives from solveExercisesService
	$scope.initQuestions = function(){
		if($rootScope.questions.length == 0){
			solveExercisesService.searchQuestions($rootScope.exerciseToEdit.exercicio.idExercicio).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, question) {
						
						$rootScope.questions.push(angular.copy(question));
						$rootScope.questions[index].alternativas = [];
						$rootScope.questions[index].resposta = "";
						
						solveExercisesService.searchAlternatives($rootScope.questions[index].idQuestao).then( 
								function successCallback(response) {
									
									$(response.data).each(function(indexAlternative, alternative) {
										$rootScope.questions[index].alternativas.push(angular.copy(alternative));
									});
									
								}, 
								function errorCallback(response) {
									
								}
							);
					});
					
				}, 
				function errorCallback(response) {
					
				}
			);
		}
	};
	
	// Method called when the vocabulary screen is open. It calls the method searchVocabularyPictures from solveExercisesService
	$scope.initVocabulary = function(){
		if($rootScope.vocabularyPictures.length == 0){
			solveExercisesService.searchVocabularyPictures($rootScope.exerciseToEdit.exercicio.idExercicio).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, picture) {
						
						picture.resposta = "";
						$rootScope.vocabularyPictures.push(angular.copy(picture));
						console.log("Teste");
					});
					
				}, 
				function errorCallback(response) {
					
				}
			);
		}
	};
	
	// Method called when the grammar screen is open. It calls the method searchGrammarQuestions from solveExercisesService
	$scope.initGrammar = function(){
		if($rootScope.grammarQuestions.length == 0){
			solveExercisesService.searchGrammarQuestions($rootScope.exerciseToEdit.exercicio.idExercicio).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, question) {
						
						var definitionExists = false;
						
						if($rootScope.grammarDefinitions.length == 0){
							$rootScope.grammarDefinitions.push(question.definicaoResposta);
							var definitionExists = true;
						}else{
							for(var i = 0; i < $rootScope.grammarDefinitions.length; i++){
								if(angular.equals($rootScope.grammarDefinitions[i], question.definicaoResposta)){
									definitionExists = true;
									break;
								}
							}
						}
						
						if(!definitionExists){
							$rootScope.grammarDefinitions.push(question.definicaoResposta);
						}
						
						question.resposta = "";
						$rootScope.grammarQuestions.push(angular.copy(question));
					});
					
					// The definitions are sorted by the id
					$rootScope.grammarDefinitions.sort(function(a, b) {
						 return b.id < a.id ?  1 // if b should come earlier, push a to end
						         : b.id > a.id ? -1 // if b should come later, push a to begin
						         : 0;                   // a and b are equal
					});
					
					// The questions are sorted by the question
					$rootScope.grammarQuestions.sort(function(a, b) {
						 return b.questao < a.questao ?  1 // if b should come earlier, push a to end
						         : b.questao > a.questao ? -1 // if b should come later, push a to begin
						         : 0;                   // a and b are equal
					});
					
				}, 
				function errorCallback(response) {
					
				}
			);
		}
	};
	
	// Method called when the reading screen is open. It calls the method searchReadingQuestions and searchReadingAlternatives from solveExercisesService
	$scope.initReading = function(){
		if($rootScope.readingQuestions.length == 0){
			solveExercisesService.searchReadingQuestions($rootScope.exerciseToEdit.exercicio.idExercicio).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, question) {
						
						$rootScope.readingQuestions.push(angular.copy(question));
						$rootScope.readingQuestions[index].alternativas = [];
						$rootScope.readingQuestions[index].resposta = "";
						
						solveExercisesService.searchReadingAlternatives($rootScope.readingQuestions[index].id).then( 
								function successCallback(response) {
									
									$(response.data).each(function(indexAlternative, alternative) {
										$rootScope.readingQuestions[index].alternativas.push(angular.copy(alternative));
									});
									
								}, 
								function errorCallback(response) {
									
								}
							);
					});
					
					console.log("Teste");
				}, 
				function errorCallback(response) {
					
				}
			);
		}
	};
	
	// Method called when the pronunciation screen is open. It calls the method searchPronunciationQuestions from solveExercisesService
	$scope.initPronunciation = function(){
		if($rootScope.pronunciationQuestions.length == 0){
			solveExercisesService.searchPronunciationQuestions($rootScope.exerciseToEdit.exercicio.idExercicio).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, question) {
						
						$rootScope.pronunciationQuestions.push(angular.copy(question));
						$rootScope.pronunciationQuestions[index].partes = [];
						//$rootScope.pronunciationQuestions[index].resposta = "";
						
						solveExercisesService.searchPronunciationParts($rootScope.pronunciationQuestions[index].id).then( 
								function successCallback(response) {
									
									$(response.data).each(function(indexPart, part) {
										part.resposta = "";
										$rootScope.pronunciationQuestions[index].partes.push(angular.copy(part));
									});
									
								}, 
								function errorCallback(response) {
									
								}
							);
					});
					
					console.log("Teste");
				}, 
				function errorCallback(response) {
					
				}
			);
		}
		
	};
	
	// The method gets the selected exercise
	$scope.selectExercise = function(iterationExercise){
		 $scope.exercise =  angular.copy(iterationExercise);
	};
	
	// Method called by "Answer" button
	$scope.goToExerciseResolution = function(){
		 $rootScope.exerciseToEdit = (angular.copy($scope.exercise));
		 $state.go("resolveExerciseLyrics");
	}; 
	
	// The method is called each time the video ends, when it stops and also when the user submits the lyrics in the incorrect order
	$scope.incrementChances = function(){
		$rootScope.exerciseToEdit.chances++;
		$scope.updateExercise();
	};
	
	// Method called when the youtube video ends
	$scope.$on('youtube.player.ended', function ($event, player) {
		$scope.incrementChances();
	});
	
	// Method called when the youtube video stops
	$scope.$on('youtube.player.paused', function ($event, player) {
		$scope.incrementChances();
	});
	
	// Call the method updateExercise from solveExercisesService
	$scope.updateExercise = function(){
		solveExercisesService.updateExercise($rootScope.exerciseToEdit).then( 
			function successCallback(response) {
				
			}, 
			function errorCallback(response) {
				
			}
		);
	};
	
	// Method called by "Go to questions" button
	$scope.goToQuestionsResolution = function(){
		
		$scope.error = "";
		$scope.isLyricsCorrect = true;
		
		// Compares the lyrics length with the ordered list
		if($rootScope.models.lists.B.length == $scope.splittedLyrics.length){
			
			// Validates if it's in the correct order, if it's not error out
			$($scope.splittedLyrics).each(function(index, lyrics) {
				if(lyrics != $rootScope.models.lists.B[index].label){
					$scope.isLyricsCorrect = false;
					return false;
				}
			});
			
			if(!$scope.isLyricsCorrect){
				$scope.incrementChances();
				$scope.error = "The lyrics are not in the correct order.";
			}else{
				$state.go("resolveExerciseQuestions");
			}
			
		}else{
			$scope.incrementChances();
			$scope.error = "The lyrics are not in the correct order.";
		}
		
	};
	
	$scope.goToVocabulary = function(){
		$state.go("resolveExerciseVocabulary");
	};
	
	$scope.goToLanguage = function(){
		$state.go("resolveExerciseLanguage");
	};
	
	$scope.goToReading = function(){
		$state.go("resolveExerciseReading");
	};
	
	$scope.goToOralPrdocution = function(){
		$state.go("resolveExerciseOralProduction");
	};
	
	$scope.goToWriting = function(){
		$state.go("resolveExerciseWriting");
	};
	
	// Method to submit the questions resolved by the student. It calls the method submitQuestions from solveExercisesService
	$scope.submitQuestions = function(){
		
		$scope.error = "";
		var questionWithNoAnswer = false;
		
		$($rootScope.questions).each(function(index, question) {
			if(question.resposta == ""){
				questionWithNoAnswer = true;
				return false;
			}
		});
		
		if(questionWithNoAnswer){
			$scope.error = "It's needed to answer all questions.";
		}else{
			
			$rootScope.exerciseToEdit.questions = [];
			$rootScope.exerciseToEdit.questions = angular.copy($rootScope.questions);
			
			solveExercisesService.submitQuestions($rootScope.exerciseToEdit).then( 
				function successCallback(response) {
					$rootScope.exerciseToEdit.nota = response.data.nota;
					$scope.questionsSubmitted = true;
				}, 
				function errorCallback(response) {
					
				}
			);
		}
	};
	
	$scope.sayIt = function (text) {
		var utterance = new SpeechSynthesisUtterance(text);
		utterance.lang = 'en-US';
		window.speechSynthesis.speak(utterance);
	};
	
	$scope.speak = function(text){
		tts.speech({
	        src: text,
	        hl: 'en-us',
	        r: 0, 
	        c: 'mp3',
	        f: '44khz_16bit_stereo',
	        ssml: false
	    });
	};

	$scope.onerror = function(event, message) {
		console.log('onerror', event, message);
	};
	
	// Angular JS Voice
	$scope.dictateIt = function (part) {
		
		$scope.error = "";
		
		if('webkitSpeechRecognition' in window){
			part.resposta = "";
		      
			var recognition = new webkitSpeechRecognition();
			recognition.continuous = false;
			recognition.interimResults = true;
			recognition.lang = 'en-US';
			recognition.start();
		      
			recognition.onresult = function (event) {
				$scope.$apply(function() {
					for (var i = event.resultIndex; i < event.results.length; i++) {
						if (event.results[i].isFinal) {
							part.resposta  += event.results[i][0].transcript;
						}
					}
		          
		          //recognition.stop();
				});
			};
			
			recognition.onstart = function(){
				$scope.speaking = true;
			};
		      
			recognition.onspeechend = function() {
				$scope.speaking = false;
			};
			
			recognition.onerror = $scope.onerror;
		      
		}else{
			$scope.error = "Your browser is not supported. If google chrome, please upgrade!";
		}
	      
	  };
});