angular.module('app').controller("exercisesMgmtCtrl", function($scope, $sce, $timeout, $rootScope, $state, Upload, LazyRoute, exercisesManagementService, constants){
	
	$scope.assignSuccess = false;
	$scope.assignError = false;
	$scope.allSelected = false;
	$scope.basicExercisesList = [];
	$scope.alternativesToDelete = [];
	$scope.picturesToDelete = [];
	$scope.grammarAlternativesToDelete = [];
	$scope.grammarDefinitionsToDelete = [];
	$scope.readingAlternativesToDelete = [];
	$scope.readingQuestionsToDelete = [];
	$scope.pronunciationQuestionPartToDelete = [];
	$scope.pronunciationQuestionToDelete = [];
	$scope.students = [];
	$scope.pictures = [];
	$scope.definitions = [];
	$scope.alternatives = [];
	$scope.readingQuestions = [];
	$scope.pronunciationQuestions = [];
	$scope.myText = $sce.trustAsHtml("1)	So you think you can tell <b style='color: #00008b'>heaven</b> from <b style='color: #00008b'>hell</b>.");
	$scope.myText2 = $sce.trustAsHtml("2)	Can you tell a green field from a cold <b style='color: darkblue;'>steel rail?</b>");
	
	/* Temporary variables */
	$scope.picturesTemp = [];
	$scope.alternativesToDeleteTemp = [];
	$scope.picturesToDeleteTemp = [];
	$scope.grammarAlternativesToDeleteTemp = [];
	$scope.grammarDefinitionsToDeleteTemp = [];
	$scope.readingAlternativesToDeleteTemp = [];
	$scope.readingQuestionsToDeleteTemp = [];
	$scope.pronunciationQuestionPartToDeleteTemp = [];
	$scope.pronunciationQuestionToDeleteTemp = [];
	$scope.questionsToDelete = [];
	$scope.exercicioToEdit;
	$scope.exerciseSaved;
	$scope.exerciseDeleted;
	$scope.questionToEdit;
	$scope.musicName = "";
	$scope.musicSinger = "";
	$scope.musicLink = "";
	$scope.musicLyrics = "";
	$scope.name = "";
	$scope.email = "";
	$scope.writingQuestao = {writingQuestao : ""};
	$scope.listeningPractice = {letraOrdenar:""};
	$scope.grammarPractice = {
		definitionInput : "",
		alternativeInput : "",
		alternativeAnswer : "",
		readingQuestionInput : "",
		readingAlternativeInput : "",
		readingAlternativeAnswer : ""
	};
	$scope.pronunciationPractice = {
			questions : [{
				descricao : "",
				tipo : ""
			}]
	};
	$scope.definitionSelected = "";
	$scope.definitionSelectedIndex = "";
	$scope.alternativeSelected = "";
	$scope.alternativeSelectedIndex = "";
	$scope.questionSelected = "";
	$scope.questionSelectedIndex = "";
	$scope.readingAlternativeSelected = "";
	$scope.readingAlternativeSelectedIndex = "";
	$scope.pronunciationQuestionSelected = "";
	$scope.pronunciationQuestionSelectedIndex = "";
	$scope.allFieldsPopulated = false;
	
	$scope.questao = {
		pergunta : "",
		valorNota : "",
		alternativas : []
	};
	
	$scope.alternativa = {
		resposta : "",
		correta : ""
	};
	
	// TODO: Remove
	// Calls searchQuestionsByExercise from exercisesManagementService considering the selected exercise
	$scope.searchQuestionsByExercise = function(idExercicio){
		exercisesManagementService.searchQuestionsByExercise(idExercicio).then( 
			function successCallback(response) {
				$scope.exercicio.questoes = [];
				
				$(response.data).each(function(index, questao) {
					$scope.exercicio.questoes.push(angular.copy(questao));
					$scope.exercicio.questoes[index].alternativas = [];
				 });
				
				// Clear the variables
				$scope.musicName = $scope.exercicio.musica.nome;
				$scope.musicSinger = $scope.exercicio.musica.cantor;
				$scope.musicLink = $scope.exercicio.musica.link;
				$scope.musicLyrics = $scope.exercicio.musica.letra;
				
				delete $rootScope.exercicioToEdit;
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
	};
	
	// Calls searchGrammarAlternativesByExercise from exercisesManagementService considering the selected exercise
	$scope.searchGrammarAlternativesByExercise = function(idExercicio){
		
		exercisesManagementService.searchGrammarAlternativesByExercise(idExercicio).then( 
			function successCallback(response) {
				
				$(response.data).each(function(index, alternative) {
					
					for(var i = 0; i < $scope.exercicio.grammarDefinicoes.length; i++){
						var definicao = $scope.exercicio.grammarDefinicoes[i];
						
						if(alternative.definicaoResposta.id == definicao.id){
							alternative.definition = i + "";
							definicao.questoes.push(angular.copy(alternative));
							break;
						}
					}
					
				});
				
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
		
	};
	
	// Calls searchGrammarDefinitionsByExercise from exercisesManagementService considering the selected exercise
	$scope.searchGrammarDefinitionsByExercise = function(idExercicio){
		exercisesManagementService.searchGrammarDefinitionsByExercise(idExercicio).then( 
			function successCallback(response) {
				$scope.exercicio.grammarDefinicoes = [];
				
				$(response.data).each(function(index, definicao) {
					$scope.exercicio.grammarDefinicoes.push(angular.copy(definicao));
					$scope.exercicio.grammarDefinicoes[index].questoes = [];
				 });
				
				$scope.searchGrammarAlternativesByExercise($scope.exercicio.idExercicio);
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
	};
	
	// Calls searchReadingAlternativesByExercise from exercisesManagementService considering the selected exercise
	$scope.searchReadingAlternativesByExercise = function(idExercicio){
		
		exercisesManagementService.searchReadingAlternativesByExercise(idExercicio).then( 
			function successCallback(response) {
				
				$(response.data).each(function(index, alternative) {
					
					for(var i = 0; i < $scope.exercicio.readingQuestoes.length; i++){
						var questao = $scope.exercicio.readingQuestoes[i];
						
						if(alternative.questao.id == questao.id){
							alternative.correta = alternative.correta + "";
							questao.readingAlternativas.push(angular.copy(alternative));
							break;
						}
					}
					
				});
				
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
		
	};
	
	// Calls searchGrammarDefinitionsByExercise from exercisesManagementService considering the selected exercise
	$scope.searchReadingQuestionsByExercise = function(idExercicio){
		exercisesManagementService.searchReadingQuestionsByExercise(idExercicio).then( 
			function successCallback(response) {
				$scope.exercicio.readingQuestoes = [];
				
				$(response.data).each(function(index, question) {
					$scope.exercicio.readingQuestoes.push(angular.copy(question));
					$scope.exercicio.readingQuestoes[index].readingAlternativas = [];
				 });
				
				$scope.searchReadingAlternativesByExercise($scope.exercicio.idExercicio);
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
	};
	
	// Calls searchPronunciationQuestionsPartsByExercise from exercisesManagementService considering the selected exercise
	$scope.searchPronunciationQuestionsPartsByExercise = function(idExercicio){
		
		exercisesManagementService.searchPronunciationQuestionsPartsByExercise(idExercicio).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, questionPart) {
						
						for(var i = 0; i < $scope.exercicio.pronunciationQuestions.length; i++){
							var questao = $scope.exercicio.pronunciationQuestions[i];
							
							if(questionPart.pronunciationQuestao.id == questao.id){
								questao.pronunciationQuestaoPartes.push(angular.copy(questionPart));
								break;
							}
						}
						
					});
					
				}, 
				function errorCallback(response) {
					console.log('Deu errado');
				}
			);
		
	};
	
	// Calls searchPronunciationQuestionsByExercise from exercisesManagementService considering the selected exercise
	$scope.searchPronunciationQuestionsByExercise = function(idExercicio){
		exercisesManagementService.searchPronunciationQuestionsByExercise(idExercicio).then( 
			function successCallback(response) {
				$scope.exercicio.pronunciationQuestions = [];
				
				$(response.data).each(function(index, question) {
					$scope.exercicio.pronunciationQuestions.push(angular.copy(question));
					$scope.exercicio.pronunciationQuestions[index].pronunciationQuestaoPartes = [];
				 });
				
				$scope.searchPronunciationQuestionsPartsByExercise($scope.exercicio.idExercicio);
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
	};
	
	// Calls searchPicturesByExercise from exercisesManagementService considering the selected exercise
	$scope.searchPicturesByExercise = function(idExercicio){
		exercisesManagementService.searchPicturesByExercise(idExercicio).then( 
			function successCallback(response) {
				
				$scope.exercicio.pictures = [];
				
				$(response.data).each(function(index, file) {
					$scope.exercicio.pictures.push(file);
				 });
				
				console.log("asdasdasd");
				
			}, 
			function errorCallback(response) {
				console.log('Deu errado');
			}
		);
	};
	
	// Populates the temporary json with the values from the selected exercise, if it's being edited, or empty values, if it's a new one
	if(!$rootScope.exercicioToEdit){
		$scope.exercicio = {
			professorId : $rootScope.loggedUser.id,
			nivel : "",
			valorNotaMaxima : "",
			musica : {
				nome : "",
				cantor : "",
				letra : "",
				letraOrdenar : "",
				link : ""
	        },
	        writingQuestao : "",
	        questoes : [],
	        pictures : [],
	        grammarDefinicoes : [],
	        readingQuestoes : [],
	        pronunciationQuestions : []
		};
	}else{
		$scope.exercicio = angular.copy($rootScope.exercicioToEdit);
		$scope.searchPicturesByExercise($scope.exercicio.idExercicio);
		$scope.searchQuestionsByExercise($scope.exercicio.idExercicio);
		$scope.searchGrammarDefinitionsByExercise($scope.exercicio.idExercicio);
		$scope.searchReadingQuestionsByExercise($scope.exercicio.idExercicio);
		$scope.searchPronunciationQuestionsByExercise($scope.exercicio.idExercicio);
	}
	
	// Function called by "New" button
	 $scope.goToExerciseCreation = function(){
		 $rootScope.exercicioEditOrCreationMode = true;
		 $state.go("newExercise");
	 };
	 
	// Function called by "Edit" button
	 $scope.goToExerciseEdition = function(){
		 $rootScope.exercicioToEdit = (angular.copy($scope.exercicio));
		 $state.go("newExercise");
	 };
	 
	 // Function called by "Add music" button
	 $scope.clearMusicModal = function(){
		$scope.musicName = "";
		$scope.musicSinger = "";
		$scope.musicLink = "";
		$scope.musicLyrics = "";
		$("#musicModal").modal("show");
	 };
	 
	 // Function called by "Add question" button (when question list is empty)
	 $scope.clearQuestionModal = function(){
		 $scope.questionToEdit = "";
		 $scope.modalError = "";
		 $scope.questao = {
			pergunta : "",
			valorNota : "",
			alternativas : []
		};
		$scope.alternativa = {
				resposta : "",
				correta : ""
			};
		$("#questionModal").modal("show");
	 };
	 
	 // Function called by "Add question" button (when question list is not empty)
	 $scope.addNewQuestion = function(){
		 $scope.error = "";
		 $scope.exerciseSaved = false;
		 
		 var sum = 0;
		 
		 $($scope.exercicio.questoes).each(function(index, question) {
			sum = sum + question.valorNota;
		 });
		 
		// If the sum of the questions scores already reached the max exercise score, it's not allowed to add a new one
		 if(sum >= $scope.exercicio.valorNotaMaxima){
			 $scope.error = "You cannot add more questions. The sum of the questions scores registered already reached " + $scope.exercicio.valorNotaMaxima + ".";
		 }else{
			 $scope.clearQuestionModal();
		 }
	 };
	 
	 // Populates the music values to temporary exercise json (it will be sent to the service posteriorly)
	 $scope.saveMusicModal = function(){
		 $scope.exercicio.musica.nome = $scope.musicName;
		 $scope.exercicio.musica.cantor = $scope.musicSinger;
		 $scope.exercicio.musica.letra = $scope.musicLyrics;
		 $scope.exercicio.musica.link = $scope.musicLink;
		 $("#musicModal").modal("hide");
	 };
	 
	 // Add a new question to the question list if all the requirements are fine
	 $scope.saveQuestionModal = function(){
		 $scope.modalError = null;
		 var existentQuestion = false;
		 var hasCorrectOption = false;
		 var indexToEdit;
		 
		// Tem que ter 1 alternativa correta registrada
		 $($scope.questao.alternativas).each(function(index, alternativa) {
			if(alternativa.correta == true){
				hasCorrectOption = true;
				return false;
			}
		 });
		 
		 if(hasCorrectOption){
			
			// Verifica se tem outra questão com mesma pergunta
			 $($scope.exercicio.questoes).each(function(index, questao) {
				 if(questao.pergunta == $scope.questao.pergunta){
					 
					 // Se questão estiver sendo editada e houver outra questão com a mesma pergunta
					 if($scope.questionToEdit){
						 if($scope.questionToEdit != questao){
						 	existentQuestion = true;
						 }
						 
					 // Se questão não estiver sendo editada estamos criando uma com a pergunta igual à outra questão
					 }else{
						 existentQuestion = true;
					 }
				 }
				 
				 if($scope.questionToEdit == questao){
					 indexToEdit = index;
				 }
			 });
			 
			 // Se houver outra questão com a mesma pergunta monstrará o erro
			 if(existentQuestion){
				 $scope.modalError = "The question is already registered for this exercise.";
			 }else{
				 
				 // Se não houver outra questão com mesma pergunta e o registro está em edição
				 if($scope.questionToEdit){
					 $scope.exercicio.questoes[indexToEdit] = angular.copy($scope.questao);
					 
				 // Se não houver outra questão com mesma pergunta e estamos criando um registro novo
				 }else{
					 $scope.exercicio.questoes.push(angular.copy($scope.questao));
				 }
				 
				 $($scope.alternativesToDeleteTemp).each(function(index, alternativeId) {
					 $scope.alternativesToDelete.push(alternativeId);
				 });
				 
				 $("#questionModal").modal("hide");
			 }
			 
		 }else{
			 $scope.modalError = "It's needed to register the correct alternative for the question.";
		 }
		 
	 };
	 
	 // Function called by "Edit question" button. Calls searchOptionsByQuestion method from exercisesManagementService
	 $scope.editQuestionModal = function(questionSelected){
		 
		 $scope.alternativesToDeleteTemp = [];
		 $scope.modalError = "";
		 $scope.alternativa = {
			resposta : "",
			correta : ""
		};
		 $scope.questao = angular.copy(questionSelected);
		 
		 if($scope.questao.idQuestao && $scope.questao.alternativas.length == 0){
			 
			 exercisesManagementService.searchOptionsByQuestion($scope.questao.idQuestao).then( 
				function successCallback(response) {
					
					$(response.data).each(function(index, alternativa) {
						$scope.questao.alternativas.push(angular.copy(alternativa));
					 });
					
				}, 
				function errorCallback(response) {
				}
			 );
			 
		 }
		 
		 $scope.questionToEdit = questionSelected;
	 };
	 
	 // Add a new option to the question if all the requirements are fine
	 $scope.addOptionToQuestion = function(e){
		 $scope.modalError = null;
		 var hasCorrectOption = false;
		 var existentOption = false;
		 
		 if($scope.questao.alternativas.length > 0){
			 $($scope.questao.alternativas).each(function(index, alternativa) {
				if(alternativa.correta == true){
					hasCorrectOption = true;
				}
				if(alternativa.resposta == $scope.alternativa.resposta){
					existentOption = true;
				}
			 });
		 }
		 
		 if(existentOption){
			 $scope.modalError = "The option is already registered.";
		 }else{
			 if(!hasCorrectOption || (hasCorrectOption && $scope.alternativa.correta == false)){
				 $scope.questao.alternativas.push(angular.copy($scope.alternativa));
				 $scope.alternativa.resposta = "";
				 $scope.alternativa.correta = "";
				 $scope.alternativa.idAlternativa = "";
			 }else{
				 $scope.modalError = "The correct option is already registered.";
			 }
		 }
		 
		 return false;
	 };
	 
	 // Delete the option from the question
	 $scope.deleteOptionFromQuestion = function(optionToDelete){
		 for (var i = 0; i < $scope.questao.alternativas.length; i++) {
		    if ($scope.questao.alternativas[i] == optionToDelete) {
		    	
		    	if(optionToDelete.idAlternativa){
		    		$scope.alternativesToDeleteTemp.push(optionToDelete.idAlternativa);
		    	}
		    	
		    	$scope.questao.alternativas.splice(i, 1);
		        break;
		    }
		 }
		 $scope.alternativa.resposta = "";
		 $scope.alternativa.correta = "";
		 $scope.alternativa.idAlternativa = "";
	 };
	 
	 // Function called by "Delete question" button
	 $scope.deleteQuestion = function(questionToDelete){
		 for (var i = 0; i < $scope.exercicio.questoes.length; i++) {
		    if ($scope.exercicio.questoes[i] == questionToDelete) {
		    		
		    	if(questionToDelete.idQuestao){
		    		$scope.questionsToDelete.push(questionToDelete.idQuestao);
		    	}
		    	
		    	$scope.exercicio.questoes.splice(i, 1);
		        break;
		    }
		 }
	 };
	 
	 // Update the values from the option if all the requirements are fine
	 $scope.editOptionFromQuestion = function(optionToEdit){
		 $scope.modalError = null;
		 var hasCorrectOption = false;
		 var existentOption = false;
		 $($scope.questao.alternativas).each(function(index, alternativa) {
			if(optionToEdit != alternativa && alternativa.correta && $scope.alternativa.correta){
				hasCorrectOption = true;
			}
			if(optionToEdit != alternativa && alternativa.resposta == $scope.alternativa.resposta){
				existentOption = true;
			}
		 });
		 
		 if(existentOption){
			 $scope.modalError = "The option is already registered.";
		 }else if(hasCorrectOption){
			 $scope.modalError = "The correct option is already registered.";
		 }else{
			 optionToEdit.resposta = $scope.alternativa.resposta;
			 optionToEdit.correta = $scope.alternativa.correta;
			 $scope.alternativa.resposta = "";
			 $scope.alternativa.correta = "";
			 $scope.alternativa.idAlternativa = "";
		 }
		 
	 };
	 
	 // Method called to get the option selected in the screen
	 $scope.selectOption = function(iterationAlternative){
		 $scope.alternativa = angular.copy(iterationAlternative);
	 };
	 
	 // Method called to get the exercise selected in the screen
	 $scope.selectExercise = function(iterationExercise){
		 $scope.exercicio =  angular.copy(iterationExercise);
	 };
	 
	 // Call deleteAlternatives method from exercisesManagementService
	 $scope.deleteAlternatives = function(){
		 exercisesManagementService.deleteAlternatives($scope.alternativesToDelete).then( 
					function successCallback(response) {
						
					}, 
					function errorCallback(response) {
					}
			);
	 };
	 
	 // Call deleteQuestions from exercisesManagementService
	 $scope.deleteQuestions = function(){
		 exercisesManagementService.deleteQuestions($scope.questionsToDelete).then( 
					function successCallback(response) {
						
					}, 
					function errorCallback(response) {
					}
			);
	 };
	 
	 // Call deletePictures from exercisesManagementService
	 $scope.deletePictures = function(){
		 exercisesManagementService.deletePictures($scope.picturesToDelete).then( 
					function successCallback(response) {
						
					}, 
					function errorCallback(response) {
					}
			);
	 };	 
	 
	 // Call deleteGrammarAlternatives from exercisesManagementService
	 $scope.deleteGrammarAlternatives = function(){
		 exercisesManagementService.deleteGrammarAlternatives($scope.grammarAlternativesToDelete).then( 
					function successCallback(response) {
						$scope.grammarAlternativesToDelete = [];
					}, 
					function errorCallback(response) {
					}
		 );		 
	 };
	 
	// Call deleteGrammarDefinitions from exercisesManagementService
	 $scope.deleteGrammarDefinitions = function(){
		 exercisesManagementService.deleteGrammarDefinitions($scope.grammarDefinitionsToDelete).then( 
					function successCallback(response) {
						$scope.grammarDefinitionsToDelete = [];
					}, 
					function errorCallback(response) {
					}
		 );		 
	 };
	 
	// Call deleteReadingAlternativesAndQuestions from exercisesManagementService
	 $scope.deleteReadingAlternativesAndQuestions = function(){
		 exercisesManagementService.deleteReadingAlternativesAndQuestions($scope.readingAlternativesToDelete, $scope.readingQuestionsToDelete).then( 
					function successCallback(response) {
						$scope.readingAlternativesToDelete = [];
						$scope.readingQuestionsToDelete = [];
					}, 
					function errorCallback(response) {
					}
		 );		 
	 };
	 
	// Call deletePronunciationPartsAndQuestions from exercisesManagementService
	 $scope.deletePronunciationPartsAndQuestions = function(){
		 exercisesManagementService.deletePronunciationPartsAndQuestions($scope.pronunciationQuestionPartToDelete, $scope.pronunciationQuestionToDelete).then( 
					function successCallback(response) {
						$scope.pronunciationQuestionPartToDelete = [];
						$scope.pronunciationQuestionToDelete = [];
					}, 
					function errorCallback(response) {
					}
		 );	
	 };
	 
	 // Call the method deleteExercise from exercisesManagementService
	 $scope.deleteExercise = function(){
		 exercisesManagementService.deleteExercise($scope.exercicio.idExercicio).then( 
					function successCallback(response) {
						
						if($scope.exercicio.nivel == 'Basic'){
							for (var i = 0; i < $scope.basicExercisesList.length; i++) {
							    if ($scope.basicExercisesList[i].idExercicio == $scope.exercicio.idExercicio) {
							    	$scope.basicExercisesList.splice(i, 1);
							        break;
							    }
							}
						}
						
						if($scope.exercicio.nivel == 'Intermediate'){
							for (var i = 0; i < $scope.intermediateExercisesList.length; i++) {
							    if ($scope.intermediateExercisesList[i].idExercicio == $scope.exercicio.idExercicio) {
							    	$scope.intermediateExercisesList.splice(i, 1);
							        break;
							    }
							}
						}
						
						if($scope.exercicio.nivel == 'Advanced'){
							for (var i = 0; i < $scope.advancedExercisesList.length; i++) {
							    if ($scope.advancedExercisesList[i].idExercicio == $scope.exercicio.idExercicio) {
							    	$scope.advancedExercisesList.splice(i, 1);
							        break;
							    }
							}
						}
						
						$scope.exercicio = "";
						$scope.exerciseDeleted = true;
					}, 
					function errorCallback(response) {
					}
			);
	 };
	 
	 // Call the method saveExercise from exercisesManagementService if all the requirements all fine
	 $scope.saveExercise = function(){
		 
		 var sum = 0;
		 $scope.exerciseSaved = false;
		 $scope.error = "";
		 
		 // Validate if the sum of the question scores is higher than the exercise score
		 $($scope.exercicio.questoes).each(function(index, question) {
			sum = sum + parseInt(question.valorNota)
		 });
		 
		 if(sum > $scope.exercicio.valorNotaMaxima){
			 $scope.error = "The sum of the questions scores is higher than " + $scope.exercicio.valorNotaMaxima + ".";
		 }else{
			 
			 if(!$scope.exercicio.professorId){
				 $scope.exercicio.professorId = $scope.exercicio.professor.id; 
			 }
			 
			 // TODO: Remover			 
			 if($scope.alternativesToDelete.length > 0){
				 $scope.deleteAlternatives();
			 }
			
			 // TODO: Remover
			 if($scope.questionsToDelete.length > 0){
				 $scope.deleteQuestions();
			 }
			 
			 if($scope.picturesToDelete.length > 0){
				 $scope.deletePictures();
			 }
			 
			 if($scope.grammarAlternativesToDelete.length > 0){
				 $scope.deleteGrammarAlternatives();
			 }
			 
			 if($scope.grammarDefinitionsToDelete.length > 0){
				 $scope.deleteGrammarDefinitions();
			 }
			 
			 if($scope.readingAlternativesToDelete.length > 0 || $scope.readingQuestionsToDelete.length > 0){
				 $scope.deleteReadingAlternativesAndQuestions();
			 }
			 
			 if($scope.pronunciationQuestionPartToDelete.length > 0 || $scope.pronunciationQuestionToDelete.length > 0){
				 $scope.deletePronunciationPartsAndQuestions();
			 }
			 
			 exercisesManagementService.saveExercise($scope.exercicio).then( 
				function successCallback(response) {
					angular.forEach($scope.exercicio.pictures, function(file) {
						$scope.picturesTemp.push(file);
					 });
					$scope.savePictures(response.data.idExercicio);
					$scope.exercicio = angular.copy(response.data);
					$scope.searchQuestionsByExercise($scope.exercicio.idExercicio);
					$scope.searchGrammarDefinitionsByExercise($scope.exercicio.idExercicio);
					$scope.searchReadingQuestionsByExercise($scope.exercicio.idExercicio);
					$scope.searchPronunciationQuestionsByExercise($scope.exercicio.idExercicio);
					$scope.exerciseSaved = true;
					$scope.alternativesToDelete = [];
					$scope.questionsToDelete = [];
				}, 
				function errorCallback(response) {
					$scope.error = response.data.status;
				}
			); 
		 }
	 };
	 
	 // Method called when the main screen from exercises management opens
	 $scope.init = function(){
		exercisesManagementService.searchAll($rootScope.loggedUser.id).then( 
			function successCallback(response) {
				
				$scope.intermediateExercisesList = [];
				$scope.advancedExercisesList = [];
				
				$(response.data).each(function(index, exercicio) {
					if(exercicio.nivel == 'Basic'){
						$scope.basicExercisesList.push(angular.copy(exercicio));
					}
					
					if(exercicio.nivel == 'Intermediate'){
						$scope.intermediateExercisesList.push(angular.copy(exercicio));
					}
					
					if(exercicio.nivel == 'Advanced'){
						$scope.advancedExercisesList.push(angular.copy(exercicio));
					}
				});
				
			}, 
			function errorCallback(response) {
			}
		);
	 };
	 
	 // Method used to search the students when the exercise is being assigned. It calls searchStudents method from exercisesManagementService
	 $scope.searchStudents = function(){
			
		$scope.students = [];
		
		exercisesManagementService.searchStudents($scope.name, $scope.email, $rootScope.loggedUser.id).then( 
				function successCallback(response) {
					$(response.data).each(function(index, student) {
						student.selected;
						$scope.students.push(angular.copy(student));
					 });
				}, 
				function errorCallback(response) {
					
				}
		);
		
	};
	
	// Method used to highlight the records selected in the lists
	$scope.rowHighilited = function(row){
      $scope.selectedRow = row;    
    };
    
    // Method used to select all students when the exercise is being assigned
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
	
	// Method used to deselect all the students when the exercise is being assigned
	$scope.deselectHeader = function(){
		$scope.allSelected = false;
	};
	
	// Call the method assignExercise from exercisesManagementService when the exercise is being assigned
	$scope.assignExercise = function(){
		var selected = false;
		$scope.assignError = "";
		$scope.assignSuccess = "";
		$scope.studentsIds = [];
		
		$($scope.students).each(function(index, student) {
			if(student.selected){
				$scope.studentsIds.push(student.id);
				selected = true;
			}
		 });
		
		if(!selected){
			$scope.assignError = "There isn't any student selected.";
		}else{
			
			exercisesManagementService.assignExercise($scope.studentsIds, $scope.exercicio.idExercicio).then( 
				function successCallback(response) {
					$scope.assignSuccess = true;
				}, 
				function errorCallback(response) {
					
				}
			);
			
		}
	};
	
	// Method called by "Assign" button
	$scope.clearAssignModal = function(){
		$scope.name = "";
		$scope.email = "";
		$scope.assignError = "";
		$scope.assignSuccess = "";
		$scope.students = [];
	};
	
	 $scope.activitiesModal = function(){
		 if(!$scope.exercicio.pictures && $scope.picturesTemp){
			 $scope.exercicio.pictures = [];
			 
			 angular.forEach($scope.picturesTemp, function(file) {
				 $scope.exercicio.pictures.push(file);
			 });
		 }
		 
		$scope.writingQuestao.writingQuestao = $scope.exercicio.writingQuestao;
		
		$scope.listeningPractice.letraOrdenar =  angular.copy($scope.exercicio.musica.letraOrdenar);
		if($scope.exercicio.grammarDefinicoes != null && $scope.exercicio.grammarDefinicoes.length > 0){
			$scope.definitions = angular.copy($scope.exercicio.grammarDefinicoes);
		}
		if($scope.exercicio.readingQuestoes != null && $scope.exercicio.readingQuestoes.length > 0){
			$scope.readingQuestions = angular.copy($scope.exercicio.readingQuestoes);
		}
		if($scope.exercicio.pronunciationQuestions != null && $scope.exercicio.pronunciationQuestions.length > 0){
			$scope.pronunciationQuestions = angular.copy($scope.exercicio.pronunciationQuestions);
		}
		
		$($scope.pronunciationQuestions).each(function(index, question) {
			
			var descricao = (index + 1) + ") ";
			  
			$(question.pronunciationQuestaoPartes).each(function(index, questionTemp) {
				//questionTemp.sequencia = index;
				  
				if(index > 0){
					descricao = descricao.concat(" ");
				}
				  
				if(questionTemp.tipo == "Gap"){
					descricao = descricao.concat("<b style='color: #00008b'>" + questionTemp.descricao + "</b>");
				}else{
					descricao = descricao.concat(questionTemp.descricao);
				}
			});
			  
			question.descricao = $sce.trustAsHtml(descricao);
			
		});
		
		angular.forEach($scope.exercicio.pictures, function(file) {
			$scope.pictures.push(file);
		});
		
		angular.forEach($scope.exercicio.grammarDefinicoes, function(definicao){
			if(definicao.questoes != null && definicao.questoes.length > 0){
				angular.forEach(definicao.questoes, function(questao){
					$scope.alternatives.push(angular.copy(questao));
				});
			}
		});
		
		$("#activitiesModal").modal("show");
	 };
	
	$scope.clearActivitiesModal = function(){
		$scope.pictures = [];
		$scope.picturesTemp = [];
		$scope.listeningPractice.letraOrdenar = "";
		$scope.writingQuestao.writingQuestao = "";
		$scope.definitions = [];
		$scope.alternatives = [];
		$scope.readingQuestions = [];
		$scope.pronunciationQuestions = [];
		$scope.picturesToDeleteTemp = [];
		$scope.grammarAlternativesToDeleteTemp = [];
		$scope.grammarDefinitionsToDeleteTemp = [];
		$scope.readingAlternativesToDeleteTemp = [];
		$scope.readingQuestionsToDeleteTemp = [];
		$scope.pronunciationQuestionPartToDeleteTemp = [];
		$scope.pronunciationQuestionToDeleteTemp = [];
		$scope.modalError = "";
		
		$scope.grammarPractice.definitionInput = "";
		$scope.grammarPractice.alternativeInput = "";
		$scope.grammarPractice.alternativeAnswer = "";
		$scope.grammarPractice.readingQuestionInput = "";
		$scope.grammarPractice.readingAlternativeInput = "";
		$scope.grammarPractice.readingAlternativeAnswer = "";
		$scope.definitionSelected = "";
		$scope.definitionSelectedIndex = "";
		$scope.alternativeSelected = "";
		$scope.alternativeSelectedIndex = "";
		$scope.questionSelected = "";
		$scope.questionSelectedIndex = "";
		
		$scope.pronunciationPractice = {
			questions : [{
				descricao : "",
				tipo : ""
			}]
		};
	};
	 
	$scope.saveActivitiesModal = function(){
		$scope.exercicio.pictures = [];
		$scope.exercicio.writingQuestao = angular.copy($scope.writingQuestao.writingQuestao);
		
		angular.forEach($scope.pictures, function(file) {
			$scope.exercicio.pictures.push(file);
		});
		
		 $($scope.picturesToDeleteTemp).each(function(index, pictureId) {
			 $scope.picturesToDelete.push(pictureId);
		 });
		 
		 $($scope.grammarAlternativesToDeleteTemp).each(function(index, grammarAlternativeId) {
			 $scope.grammarAlternativesToDelete.push(grammarAlternativeId);
		 });
		 
		 $($scope.grammarDefinitionsToDeleteTemp).each(function(index, definitionId) {
			 $scope.grammarDefinitionsToDelete.push(definitionId);
		 });
		 
		 $($scope.readingAlternativesToDeleteTemp).each(function(index, readingAlternativeId) {
			 $scope.readingAlternativesToDelete.push(readingAlternativeId);
		 });
		 
		 $($scope.readingQuestionsToDeleteTemp).each(function(index, readingQuestionId) {
			 $scope.readingQuestionsToDelete.push(readingQuestionId);
		 });
		 
		 $($scope.pronunciationQuestionPartToDeleteTemp).each(function(index, pronunciationQuestionPartId) {
			 $scope.pronunciationQuestionPartToDelete.push(pronunciationQuestionPartId);
		 });
		 
		 $($scope.pronunciationQuestionToDeleteTemp).each(function(index, pronunciationQuestionId) {
			 $scope.pronunciationQuestionToDelete.push(pronunciationQuestionId);
		 });
		 
		$scope.exercicio.musica.letraOrdenar = angular.copy($scope.listeningPractice.letraOrdenar);
		$scope.exercicio.grammarDefinicoes = angular.copy($scope.definitions);
		$scope.exercicio.readingQuestoes = angular.copy($scope.readingQuestions);
		
		angular.forEach($scope.exercicio.grammarDefinicoes, function(definicao){
			definicao.questoes = [];
		});
		angular.forEach($scope.alternatives, function(questao){
			$scope.exercicio.grammarDefinicoes[questao.definition].questoes.push(angular.copy(questao));
		});
		
		$scope.exercicio.pronunciationQuestions = angular.copy($scope.pronunciationQuestions);
		$scope.clearActivitiesModal();
		$("#activitiesModal").modal("hide");
	};
	
	$scope.addFileToList = function(file){
		var picture = {
				file : file,
				nome : ""
		};
		$scope.pictures.push(picture);
	};
	  
	$scope.deletePicture = function(pictureToRemove){
		for (var i = 0; i < $scope.pictures.length; i++) {
		    if ($scope.pictures[i] == pictureToRemove) {
		    	if(pictureToRemove.id){
		    		$scope.picturesToDeleteTemp.push(pictureToRemove.id);
		    	}
		    	$scope.pictures.splice(i, 1);
		        break;
		    }
		}
	};
	
	  $scope.savePictures = function(idExercicio) {
	    
		  angular.forEach($scope.exercicio.pictures, function(file) {
			  
			  if(file.id){
				  file.upload = Upload.upload({
			      	url: constants.baseUrl + '/updateFile',
			        data: {'idExercicio': idExercicio, 'nomeImagem' : file.nome, 'id' : file.id}
			      });
			  }else{
				  file.upload = Upload.upload({
			      	url: constants.baseUrl + '/saveFile',
			        data: {file: file.file, 'idExercicio': idExercicio, 'nomeImagem' : file.nome}
			      });
			  }
	
		      file.upload.then(function (response) {
		    	  file.id = response.data.id;
		    	  file.base64 = response.data.base64;
		    	  console.log("eeeee");
		        $timeout(function () {
		          //file.result = response.data;
		        });
		      }, function (response) {
		        if (response.status > 0)
		          $scope.errorMsg = response.status + ': ' + response.data;
		      }, function (evt) {
		        file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		      });
		    });
		  
	  };
	  
	  $scope.selectDefinition = function(definition, index){
		  $scope.definitionSelected = definition;
		  $scope.definitionSelectedIndex = index;
	  };
	  
	  $scope.addDefinition = function(){
		  
		  $scope.modalError = "";
		  var existentDefinition = false;
		  
		  angular.forEach($scope.definitions, function(definition) {
			  if(definition.definicao.trim() == $scope.grammarPractice.definitionInput.trim()){
				  existentDefinition = true;
			  }
		  });
		  
		  if(existentDefinition){
		  	$scope.modalError = "The definition is already registered for this exercise.";
		  }else{
			
			  var definition = {
					  definicao : angular.copy($scope.grammarPractice.definitionInput)
			  };
			  $scope.definitions.push(angular.copy(definition));
			  $scope.grammarPractice.definitionInput = "";
			  
		  }
		  
	  };
	  
	  $scope.editDefinition = function(){
		  
		  $scope.modalError = "";
		  var existentDefinition = false;
		  
		  $($scope.definitions).each(function(index, definition) {
			  if(index != $scope.definitionSelectedIndex && definition.definicao == $scope.grammarPractice.definitionInput){
				  existentDefinition = true;
			  }
		  });
		  
		  if(existentDefinition){
			  $scope.modalError = "The definition is already registered for this exercise.";
		  }else{
			  $scope.definitions[$scope.definitionSelectedIndex].definicao = angular.copy($scope.grammarPractice.definitionInput);
			  $scope.grammarPractice.definitionInput = "";
			  $scope.definitionSelected = "";
		  }
	  };
	  
	  $scope.deleteDefinition = function(){
		  if($scope.definitionSelected.id){
			  $scope.grammarDefinitionsToDeleteTemp.push($scope.definitionSelected.id);
		  }
		  
		  for (var i = 0; i < $scope.alternatives.length; i++) {
			  if($scope.alternatives[i].definition == $scope.definitionSelectedIndex + ""){
				  $scope.alternativeSelected = $scope.alternatives[i];
				  $scope.alternativeSelectedIndex = i;
				  $scope.deleteAlternative();
			  }
		  }
			  
		  $scope.definitions.splice($scope.definitionSelectedIndex, 1);
		  
		  $($scope.definitions).each(function(indexDefinition, definition) {
			  $($scope.alternatives).each(function(indexAlternative, alternative) {
				  if(alternative.definicaoResposta.definicao == definition.definicao){
					  alternative.definition = indexDefinition + "";
				  }
			  });
		  });
		  
		  $scope.grammarPractice.definitionInput = "";
		  $scope.definitionSelected = "";
	  };
	  
	  $scope.selectAlternative = function(alternative, index){
		  $scope.alternativeSelected = alternative;
		  $scope.alternativeSelectedIndex = index;
	  };
	  
	  $scope.addAlternative = function(){
		  var alternative = {
				  questao : $scope.grammarPractice.alternativeInput,
				  definition : $scope.grammarPractice.alternativeAnswer,
				  definicaoResposta : $scope.definitions[Number($scope.grammarPractice.alternativeAnswer)]
	  	  };
		  $scope.alternatives.push(alternative);
		  $scope.grammarPractice.alternativeInput = "";
		  $scope.grammarPractice.alternativeAnswer = "";
	  };
	  
	  $scope.editAlternative = function(){
		  $scope.alternatives[$scope.alternativeSelectedIndex].alternative = angular.copy($scope.grammarPractice.alternativeInput);
		  $scope.alternatives[$scope.alternativeSelectedIndex].definition = angular.copy($scope.grammarPractice.alternativeAnswer);
		  $scope.grammarPractice.alternativeInput = "";
		  $scope.grammarPractice.alternativeAnswer = "";
		  $scope.alternativeSelected = "";
	  };
	  
	  $scope.deleteAlternative = function(){
		  if($scope.alternativeSelected.id){
			  $scope.grammarAlternativesToDeleteTemp.push($scope.alternativeSelected.id);
		  }
		  $scope.alternatives.splice($scope.alternativeSelectedIndex, 1);
		  $scope.grammarPractice.alternativeInput = "";
		  $scope.grammarPractice.alternativeAnswer = "";
		  $scope.alternativeSelected = "";
	  };
	  
	  $scope.selectQuestion = function(question, index){
		  $scope.questionSelected = question;
		  $scope.questionSelectedIndex = index;
		  $scope.readingAlternativeSelected = "";
	  };
	  
	  $scope.addQuestion = function(){
		  var existentDefinition = false;
		  $scope.modalError = "";
		  
		  angular.forEach($scope.readingQuestions, function(question) {
			  if(question.pergunta.trim() == $scope.grammarPractice.readingQuestionInput.trim()){
				  existentDefinition = true;
			  }
		  });
		  
		  if(existentDefinition){
				$scope.modalError = "The question is already registered for this exercise.";
		  }else{
			  
			  var question = {
				  pergunta : angular.copy($scope.grammarPractice.readingQuestionInput),
				  readingAlternativas : []
			  };
			  $scope.readingQuestions.push(angular.copy(question));
			  $scope.grammarPractice.readingQuestionInput = "";
			  $scope.grammarPractice.readingAlternativeInput = "";
			  $scope.grammarPractice.readingAlternativeAnswer = "";
			  $scope.questionSelected = "";
			  
		  }
		  
	  };
	  
	  $scope.editQuestion = function(){
		  $scope.modalError = "";
		  $scope.readingQuestions[$scope.questionSelectedIndex].pergunta = angular.copy($scope.grammarPractice.readingQuestionInput);
		  $scope.grammarPractice.readingQuestionInput = "";
		  $scope.grammarPractice.readingAlternativeInput = "";
		  $scope.grammarPractice.readingAlternativeAnswer = "";
		  $scope.questionSelected = "";
	  };
	  
	  $scope.deleteQuestion = function(){
		  if($scope.questionSelected.id){
			  $scope.readingQuestionsToDeleteTemp.push($scope.questionSelected.id);
		  }
		  
		  $($scope.questionSelected.readingAlternativas).each(function(index, alternative) {
			  $scope.readingAlternativeSelectedIndex = index;
			  $scope.readingAlternativeSelected = alternative;
			  $scope.deleteReadingAlternative();
		  });
		  
		  $scope.readingQuestions.splice($scope.questionSelectedIndex, 1);
		  $scope.grammarPractice.readingQuestionInput = "";
		  $scope.questionSelected = "";
	  };
	  
	  $scope.selectReadingAlternative = function(alternative, index){
		  $scope.readingAlternativeSelected = alternative;
		  $scope.readingAlternativeSelectedIndex = index;
	  };
	  
	  $scope.addReadingAlternative = function(){
		  var alternative = {
				  descricao : $scope.grammarPractice.readingAlternativeInput,
				  correta : $scope.grammarPractice.readingAlternativeAnswer
	  	  };
		  $scope.questionSelected.readingAlternativas.push(alternative);
		  $scope.grammarPractice.readingAlternativeInput = "";
		  $scope.grammarPractice.readingAlternativeAnswer = "";
		  $scope.readingAlternativeSelected = "";
	  };
	  
	  $scope.editReadingAlternative = function(){
		  $scope.questionSelected.readingAlternativas[$scope.readingAlternativeSelectedIndex].descricao = angular.copy($scope.grammarPractice.readingAlternativeInput);
		  $scope.questionSelected.readingAlternativas[$scope.readingAlternativeSelectedIndex].correta = angular.copy($scope.grammarPractice.readingAlternativeAnswer);
		  $scope.grammarPractice.readingAlternativeInput = "";
		  $scope.grammarPractice.readingAlternativeAnswer = "";
		  $scope.readingAlternativeSelected = "";
	  };
	  
	  $scope.deleteReadingAlternative = function(){
		  if($scope.readingAlternativeSelected.id){
			  $scope.readingAlternativesToDeleteTemp.push($scope.readingAlternativeSelected.id);
		  }
		  $scope.questionSelected.readingAlternativas.splice($scope.readingAlternativeSelectedIndex, 1);
		  $scope.grammarPractice.readingAlternativeInput = "";
		  $scope.grammarPractice.readingAlternativeAnswer = "";
		  $scope.readingAlternativeSelected = "";
	  };
	  
	  $scope.selectPronunciationQuestion = function(question, index){
		  $scope.pronunciationQuestionSelected = question;
		  $scope.pronunciationQuestionSelectedIndex = index;
		  
		  if($scope.pronunciationQuestionSelected.pronunciationQuestaoPartes.length == 0){
			  $scope.pronunciationPractice.questions = [{
					descricao : "",
					tipo : ""
			  }];
		  }else{
			  $scope.pronunciationPractice.questions = angular.copy($scope.pronunciationQuestionSelected.pronunciationQuestaoPartes);
		  }
		  
		  $scope.evaluateFieldsPopulated();
	  };
	  
	  $scope.addPronunciationQuestion = function(){
		  var descricao = ($scope.pronunciationQuestions.length + 1) + ") ";
		  var question = {
				  descricao : "",
				  pronunciationQuestaoPartes : []
		  };
		  
		  $($scope.pronunciationPractice.questions).each(function(index, questionTemp) {
			  questionTemp.sequencia = index;
			  question.pronunciationQuestaoPartes.push(angular.copy(questionTemp));
			  
			  if(index > 0){
				  descricao = descricao.concat(" ");
			  }
			  
			  if(questionTemp.tipo == "Gap"){
				  descricao = descricao.concat("<b style='color: #00008b'>" + questionTemp.descricao + "</b>");
			  }else{
				  descricao = descricao.concat(questionTemp.descricao);
			  }
		  });
		  
		  question.descricao = $sce.trustAsHtml(descricao);
		  
		  $scope.pronunciationQuestions.push(question);
		  $scope.pronunciationQuestionSelected = "";
		  $scope.pronunciationPractice = {
					questions : [{
						descricao : "",
						tipo : ""
					}]
		  };
		  $scope.evaluateFieldsPopulated();
	  };
	  
	  $scope.editPronunciationQuestion = function(){
		  var descricao = ($scope.pronunciationQuestionSelectedIndex + 1) + ") ";
		  
		  $($scope.pronunciationPractice.questions).each(function(index, questionTemp) {
			  questionTemp.sequencia = index;
			  
			  if(index > 0){
				  descricao = descricao.concat(" ");
			  }
			  
			  if(questionTemp.tipo == "Gap"){
				  descricao = descricao.concat("<b style='color: #00008b'>" + questionTemp.descricao + "</b>");
			  }else{
				  descricao = descricao.concat(questionTemp.descricao);
			  }
		  });
		  
		  $scope.pronunciationQuestions[$scope.pronunciationQuestionSelectedIndex].descricao = $sce.trustAsHtml(descricao);
		  $scope.pronunciationQuestions[$scope.pronunciationQuestionSelectedIndex].pronunciationQuestaoPartes = angular.copy($scope.pronunciationPractice.questions);
		  
		  $scope.pronunciationQuestionSelected = "";
		  $scope.pronunciationPractice = {
					questions : [{
						descricao : "",
						tipo : ""
					}]
		  };
		  $scope.evaluateFieldsPopulated();
	  };
	  
	  $scope.addLine = function(){
		  var question = {
				  descricao : "",
				  tipo : ""
		  };
		  $scope.pronunciationPractice.questions.push(question);
		  $scope.evaluateFieldsPopulated();
	  };
	  
	  $scope.deletePronunciationQuestion = function(){
		  if($scope.pronunciationQuestionSelected.id){
			  $scope.pronunciationQuestionToDeleteTemp.push($scope.pronunciationQuestionSelected.id);
		  }
		  
		  $($scope.pronunciationPractice.questions).each(function(index, questionPart) {
			  if(questionPart.id){
				  $scope.pronunciationQuestionPartToDeleteTemp.push(questionPart.id);
			  }
		  });
		  
		  $scope.pronunciationQuestions.splice($scope.pronunciationQuestionSelectedIndex, 1);
		  
		  $($scope.pronunciationQuestions).each(function(indexQuestion, question) {
			  
			  var descricao = (indexQuestion + 1) + ") ";
			  
			  $(question.pronunciationQuestaoPartes).each(function(index, questionPart) {
				  
				  if(index > 0){
					  descricao = descricao.concat(" ");
				  }
				  
				  if(questionPart.tipo == "Gap"){
					  descricao = descricao.concat("<b style='color: #00008b'>" + questionPart.descricao + "</b>");
				  }else{
					  descricao = descricao.concat(questionPart.descricao);
				  }
			  });
			  
			  question.descricao = $sce.trustAsHtml(descricao);
			  
		  });
		  
		  $scope.pronunciationPractice.questions = [{
				descricao : "",
				tipo : ""
		  }];
	  };
	  
	  $scope.deletePronunciationQuestionPart = function(questionPart, index){
		  if(questionPart.id){
			  $scope.pronunciationQuestionPartToDeleteTemp.push(questionPart.id);
		  }
		  $scope.pronunciationPractice.questions.splice(index, 1);
	  };
	  
	  $scope.evaluateFieldsPopulated = function(){
		  var allFieldsPopulated = true;
		  for (var i = 0; i < $scope.pronunciationPractice.questions.length; i++){
			  var part = $scope.pronunciationPractice.questions[i];
			  if(part.descricao === "" || part.tipo === ""){
				  allFieldsPopulated = false;
				  break;
			  }
		  }
		  $scope.allFieldsPopulated = allFieldsPopulated;
	  };
	  
	  $(document).ready(function () {
		  $('textarea[data-limit-rows=true]')
		    .on('keypress', function (event) {
		        var textarea = $(this),
		            text = textarea.val(),
		            numberOfLines = (text.match(/\n/g) || []).length + 1,
		            maxRows = parseInt(textarea.attr('rows'));
	
		        if (event.which === 13 && numberOfLines === maxRows ) {
		          return false;
		        }
		    })
		});
	  
});