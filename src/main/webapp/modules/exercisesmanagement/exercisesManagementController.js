angular.module('app').controller("exercisesMgmtCtrl", function($scope, $q, $sce, $timeout, $rootScope, $state, Upload, LazyRoute, exercisesManagementService, constants){
	
	$scope.dataLoading = false;
	$scope.assignSuccess = false;
	$scope.assignError = false;
	$scope.allSelected = false;
	$scope.basicExercisesList = [];
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
	
	// Function called by "New" button
	 $scope.goToExerciseCreation = function(){
		 $scope.dataLoading = true;
		 $rootScope.exercicioEditOrCreationMode = true;
		 delete $rootScope.exercicioToEdit;
		 $scope.dataLoading = false;
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
	 
	 // Populates the music values to temporary exercise json (it will be sent to the service posteriorly)
	 $scope.saveMusicModal = function(){
		 $scope.exercicio.musica.nome = $scope.musicName;
		 $scope.exercicio.musica.cantor = $scope.musicSinger;
		 $scope.exercicio.musica.letra = $scope.musicLyrics;
		 $scope.exercicio.musica.link = $scope.musicLink;
		 $("#musicModal").modal("hide");
	 };
	 
	 // Method called to get the exercise selected in the screen
	 $scope.selectExercise = function(iterationExercise){
		 $scope.exercicio =  angular.copy(iterationExercise);
	 };
	 
	 // Call deletePictures from exercisesManagementService
	 $scope.deletePictures = function(){
		 exercisesManagementService.deletePictures($scope.picturesToDelete).then( 
					function successCallback(response) {
						
					}, 
					function errorCallback(response) {
						$scope.dataLoading = false;
					}
			);
	 };	 
	 
	// Call deleteGrammarAlternativesAndQuestions from exercisesManagementService
	 $scope.deleteGrammarAlternativesAndQuestions = function(){
		 exercisesManagementService.deleteGrammarAlternativesAndQuestions($scope.grammarAlternativesToDelete, $scope.grammarDefinitionsToDelete).then( 
					function successCallback(response) {
						$scope.grammarAlternativesToDelete = [];
						$scope.grammarDefinitionsToDelete = [];
					}, 
					function errorCallback(response) {
						$scope.dataLoading = false;
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
						$scope.dataLoading = false;
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
						$scope.dataLoading = false;
					}
		 );	
	 };
	 
	 // Call the method deleteExercise from exercisesManagementService
	 $scope.deleteExercise = function(){
		 
		 $scope.dataLoading = true;
		 $scope.exerciseDeleted = false;
		 $scope.error = "";
		 
		 exercisesManagementService.searchUnresolvedExercises($scope.exercicio.idExercicio).then( 
				 function successCallback(response) {
					 if(response.data.length > 0){
						 
						 $scope.error = "The lesson is being resolved by a student and cannot be deleted.";
						 $scope.dataLoading = false;
						 
					 }else{
						 
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
									 $scope.dataLoading = false;
								 }, 
								 function errorCallback(response) {
									 $scope.dataLoading = false;
								 }
						 );
						 
					 }
				 }, 
				 function errorCallback(response) {
					 $scope.dataLoading = false;
				 }
		 );
		 
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
	 
	 // Call the method saveExercise from exercisesManagementService if all the requirements all fine
	 $scope.saveExercise = function(){
		 
		 $scope.dataLoading = true;
		 $scope.exerciseSaved = false;
		 $scope.error = "";
		 
		 var promises = [];
		 
		 if(!$scope.exercicio.professorId){
			 $scope.exercicio.professorId = $scope.exercicio.professor.id; 
		 }
		 
		 if($scope.picturesToDelete.length > 0){
			 promises.push($scope.deletePictures());
		 }
		 
		 if($scope.grammarAlternativesToDelete.length > 0 || $scope.grammarDefinitionsToDelete.length > 0){
			 promises.push($scope.deleteGrammarAlternativesAndQuestions());
		 }
		 
		 if($scope.readingAlternativesToDelete.length > 0 || $scope.readingQuestionsToDelete.length > 0){
			 promises.push($scope.deleteReadingAlternativesAndQuestions());
		 }
		 
		 if($scope.pronunciationQuestionPartToDelete.length > 0 || $scope.pronunciationQuestionToDelete.length > 0){
			 promises.push($scope.deletePronunciationPartsAndQuestions());
		 }
		 
		 // Execute the operations above, if needed
		 $q.all(promises).then(function() {
			 
			 exercisesManagementService.saveExercise($scope.exercicio).then( 
				function successCallback(response) {
					angular.forEach($scope.exercicio.pictures, function(file) {
						$scope.picturesTemp.push(file);
					 });
					
					var promises2 = [];
					promises2.push($scope.savePictures(response.data.idExercicio));
					
					// Save the pictures
					$q.all(promises2).then(function() {
						
						$scope.exercicio = angular.copy(response.data);
						
						var promises3 = [];
						
						promises3.push($scope.searchGrammarDefinitionsByExercise($scope.exercicio.idExercicio));
						promises3.push($scope.searchReadingQuestionsByExercise($scope.exercicio.idExercicio));
						promises3.push($scope.searchPronunciationQuestionsByExercise($scope.exercicio.idExercicio));
						
						// Search the records
						$q.all(promises3).then(function() {
							$scope.exerciseSaved = true;
							$scope.dataLoading = false;
						});
						
					});
					
				}, 
				function errorCallback(response) {
					$scope.error = response.data.status;
					$scope.dataLoading = false;
				}
			); 
			 
         });
		 
	 };
	 
	 $scope.initLessonAddOrEdit = function(){
		 
		$scope.dataLoading = true;
		 
		// Populates the temporary json with the values from the selected exercise, if it's being edited, or empty values, if it's a new one
		if(!$rootScope.exercicioToEdit){
			$scope.exercicio = {
				professorId : $rootScope.loggedUser.id,
				nivel : "",
				musica : {
					nome : "",
					cantor : "",
					letra : "",
					letraOrdenar : "",
					link : ""
		        },
		        writingQuestao : "",
		        pictures : [],
		        grammarDefinicoes : [],
		        readingQuestoes : [],
		        pronunciationQuestions : []
			};
			
			$scope.dataLoading = false;
			
		}else{
			$scope.exercicio = angular.copy($rootScope.exercicioToEdit);
			
			var promises = [];
			promises.push($scope.searchGrammarDefinitionsByExercise($scope.exercicio.idExercicio));
			promises.push($scope.searchReadingQuestionsByExercise($scope.exercicio.idExercicio));
			promises.push($scope.searchPronunciationQuestionsByExercise($scope.exercicio.idExercicio));
			
			$q.all(promises).then(function() {
				
				exercisesManagementService.searchPicturesByExercise($scope.exercicio.idExercicio).then( 
					function successCallback(response) {
						
						$scope.exercicio.pictures = [];
						
						$(response.data).each(function(index, file) {
							$scope.exercicio.pictures.push(file);
						 });
						
						// Populate music modal
						$scope.musicName = $scope.exercicio.musica.nome;
						$scope.musicSinger = $scope.exercicio.musica.cantor;
						$scope.musicLyrics = $scope.exercicio.musica.letra;
						$scope.musicLink = $scope.exercicio.musica.link;
						$scope.dataLoading = false;
						
					}, 
					function errorCallback(response) {
						$scope.dataLoading = false;
					}
				);
				
			});
			
		}
		 
	 };
	 
	 // Method called when the main screen from exercises management opens
	 $scope.init = function(){
		
		$scope.dataLoading = true;
		
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
				
				$scope.dataLoading = false;
				
			}, 
			function errorCallback(response) {
				$scope.dataLoading = false;
			}
		);
	 };
	 
	 // Method used to search the students when the exercise is being assigned. It calls searchStudents method from exercisesManagementService
	 $scope.searchStudents = function(){
		 
		$scope.dataLoading = true;
		$scope.students = [];
		
		exercisesManagementService.searchStudents($scope.name, $scope.email, $rootScope.loggedUser.id).then( 
				function successCallback(response) {
					$(response.data).each(function(index, student) {
						student.selected;
						$scope.students.push(angular.copy(student));
					 });
					
					$scope.dataLoading = false;
				}, 
				function errorCallback(response) {
					$scope.dataLoading = false;
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
		$scope.dataLoading = true;
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
					$scope.dataLoading = false;
				}, 
				function errorCallback(response) {
					$scope.dataLoading = false;
				}
			);
			
		}
	};
	
	// Method called by "Assign" button
	$scope.clearAssignModal = function(){
		$scope.dataLoading = true;
		$scope.name = "";
		$scope.email = "";
		$scope.assignError = "";
		$scope.assignSuccess = "";
		$scope.students = [];
		$scope.dataLoading = false;
	};
	
	 $scope.activitiesModal = function(){
		 
		 $scope.dataLoading = true;
		 
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
		
		$scope.dataLoading = false;
		
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
		
		$scope.dataLoading = true;
		
		$scope.modalError = "";
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
		
		$scope.dataLoading = false;
		
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
		  var alternativesToKeep = [];
		  
		  if($scope.definitionSelected.id){
			  $scope.grammarDefinitionsToDeleteTemp.push($scope.definitionSelected.id);
		  }
		  
		  for (var i = 0; i < $scope.alternatives.length; i++) {
			  if($scope.alternatives[i].definition == $scope.definitionSelectedIndex + ""){
				  if($scope.alternatives[i].id){
					  $scope.grammarAlternativesToDeleteTemp.push($scope.alternatives[i].id);
				  }
			  }else{
				  alternativesToKeep.push($scope.alternatives[i]);
			  }
		  }
			
		  $scope.alternatives = alternativesToKeep;
		  
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
				  sequencia : $scope.alternatives.length + 1,
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
		  $scope.grammarPractice.readingAlternativeInput = "";
		  $scope.grammarPractice.readingAlternativeAnswer = "";
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
				  readingAlternativas : [],
				  sequencia : $scope.readingQuestions.length + 1
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
		  $scope.modalError = "";
		  
		  if($scope.grammarPractice.readingAlternativeAnswer == "true"){
			  angular.forEach($scope.questionSelected.readingAlternativas, function(alternative) {
				  if(alternative.correta == "true"){
					  $scope.modalError = "The question can't have more than one answer.";
				  }
			  });
		  }
		  
		  if(!$scope.modalError){
			  var alternative = {
					  descricao : $scope.grammarPractice.readingAlternativeInput,
					  correta : $scope.grammarPractice.readingAlternativeAnswer,
					  sequencia : $scope.questionSelected.readingAlternativas.length + 1
		  	  };
			  $scope.questionSelected.readingAlternativas.push(alternative);
			  $scope.grammarPractice.readingAlternativeInput = "";
			  $scope.grammarPractice.readingAlternativeAnswer = "";
			  $scope.readingAlternativeSelected = "";
		  }
		  
	  };
	  
	  $scope.editReadingAlternative = function(){
		  $scope.modalError = "";
		  
		  if($scope.grammarPractice.readingAlternativeAnswer == "true"){
			  $($scope.questionSelected.readingAlternativas).each(function(index, alternative) {
				  if(index != $scope.readingAlternativeSelectedIndex && alternative.correta == "true"){
					  $scope.modalError = "The question can't have more than one answer.";
				  }
			  });
		  }
		  
		  if(!$scope.modalError){
			  $scope.questionSelected.readingAlternativas[$scope.readingAlternativeSelectedIndex].descricao = angular.copy($scope.grammarPractice.readingAlternativeInput);
			  $scope.questionSelected.readingAlternativas[$scope.readingAlternativeSelectedIndex].correta = angular.copy($scope.grammarPractice.readingAlternativeAnswer);
			  $scope.grammarPractice.readingAlternativeInput = "";
			  $scope.grammarPractice.readingAlternativeAnswer = "";
			  $scope.readingAlternativeSelected = "";
		  }
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
				  sequencia : $scope.pronunciationQuestions.length + 1,
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