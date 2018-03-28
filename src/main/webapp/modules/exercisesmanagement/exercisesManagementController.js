angular.module('app').controller("exercisesMgmtCtrl", function($scope, $rootScope, $state, Upload, LazyRoute, exercisesManagementService, constants){
	
	$scope.assignSuccess = false;
	$scope.assignError = false;
	$scope.allSelected = false;
	$scope.basicExercisesList = [];
	$scope.alternativesToDelete = [];
	$scope.students = [];
	$scope.pictures = [];
	
	/* Temporary variables */
	$scope.alternativesToDeleteTemp = [];
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
	$scope.listeningPractice = {letraOrdenar:""}
	
	$scope.questao = {
		pergunta : "",
		valorNota : "",
		alternativas : []
	};
	
	$scope.alternativa = {
		resposta : "",
		correta : ""
	};
	
	// Calls searchQuestionsByExercise from exercisesManagementService considering the selected exercise
	$scope.searchQuestionsByExercise = function(idExercicio){
		exercisesManagementService.searchQuestionsByExercise($scope.exercicio.idExercicio).then( 
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
	        questoes : [],
	        pictures : []
		};
	}else{
		$scope.exercicio = angular.copy($rootScope.exercicioToEdit);
		$scope.searchQuestionsByExercise($scope.exercicio.idExercicio);
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
			 
			 if($scope.alternativesToDelete.length > 0){
				 $scope.deleteAlternatives();
			 }
				
			 if($scope.questionsToDelete.length > 0){
				 $scope.deleteQuestions();
			 }
			 
			 exercisesManagementService.saveExercise($scope.exercicio).then( 
				function successCallback(response) {
					$scope.savePictures(response.data.idExercicio);
					$scope.exercicio = angular.copy(response.data);
					$scope.searchQuestionsByExercise($scope.exercicio.idExercicio);
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
	
	$scope.addFileToList = function(file){
		$scope.pictures.push(file);
	};
	
	 $scope.activitiesModal = function(){
		$scope.listeningPractice.letraOrdenar =  angular.copy($scope.exercicio.musica.letraOrdenar);
		angular.forEach($scope.exercicio.pictures, function(file) {
			$scope.pictures.push(file);
		});
		$("#activitiesModal").modal("show");
	 };
	
	$scope.uploadFiles = function(files, errFiles) {
	    $scope.errFiles = errFiles;
	    angular.forEach(files, function(file) {
	    	$scope.filesTest.push(file);
//	      file.upload = Upload.upload({
//	      	url: 'api/upload/file',
//	        data: {file: file}
//	      });

//	      file.upload.then(function (response) {
//	        $timeout(function () {
//	          file.result = response.data;
//	        });
//	      }, function (response) {
//	        if (response.status > 0)
//	          $scope.errorMsg = response.status + ': ' + response.data;
//	      }, function (evt) {
//	        file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
//	      });
	    });
	    console.log("Caraloh");
	  };
	  
	  $scope.removePicture = function(pictureToRemove){
		  
		  for (var i = 0; i < $scope.pictures.length; i++) {
		    if ($scope.pictures[i] == pictureToRemove) {
		    	$scope.pictures.splice(i, 1);
		        break;
		    }
		 }
		  
	  };
	  
	  $scope.saveActivitiesModal = function(){
		  $scope.exercicio.pictures = [];
		  angular.forEach($scope.pictures, function(file) {
			  $scope.exercicio.pictures.push(file);
		  });
		  $scope.exercicio.musica.letraOrdenar = angular.copy($scope.listeningPractice.letraOrdenar);
		  $scope.pictures = [];
		  $("#activitiesModal").modal("hide");
	  };
	  
	  $scope.savePictures = function(idExercicio) {
	    
//		  exercisesManagementService.test($scope.pictures, $scope.exercicio).then( 
//				function successCallback(response) {
//					$scope.assignSuccess = true;
//				}, 
//				function errorCallback(response) {
//					
//				}
//			);
		  
		  angular.forEach($scope.exercicio.pictures, function(file) {
		      file.upload = Upload.upload({
		      	url: constants.baseUrl + '/test',
		        data: {file: file, 'idExercicio': idExercicio, 'nomeImagem' : file.nome}
		      });

		      file.upload.then(function (response) {
		        $timeout(function () {
		          file.result = response.data;
		        });
		      }, function (response) {
		        if (response.status > 0)
		          $scope.errorMsg = response.status + ': ' + response.data;
		      }, function (evt) {
		        file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		      });
		    });
		  
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