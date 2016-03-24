'use strict';

angular.module('petStoreLTApp')

.controller(
		'PetCtrl',
		function($scope, petSrv) {			

			$scope.add = {};
			$scope.message = null;

			$scope.addPet = function() {
				
				$scope.message = null;

				var pet = {
					id : $scope.add.id,
					name : $scope.add.name,
					price : $scope.add.price
				}

				console.log('adding pet : ' + JSON.stringify(pet));

				$scope.promise = petSrv.addPet(pet, function success(result) {
					console.log('pet added : ' + pet);				

					$scope.getAll();
					
					if (result.data != 'OK')
						$scope.message = 'pet adding error : ' + result.data;

				}, function error(data) {		
					console.log('pet adding error' + JSON.stringify(data));
					$scope.message = 'An error has occured : ' + data.data.error;
				});
			};

			$scope.removePet = function(petId) {
				
				$scope.message = null;
				
				$scope.promise = petSrv.removePet(petId,
						function success(result) {
							console.log('pet ' + petId + ' removed');

							$scope.getAll();
							
							if (result.data != 'OK')
								$scope.message = 'pet removing error : ' + result.data;

						}, function error(data) {							
							$scope.message = 'An error has occured : ' + data.data.error;							
							console.log('pet removing error' + JSON.stringify(data));
						});
			};

			$scope.findPetById = function() {
				
				if (!$scope.search.id) {
					$scope.getAll();
				}
				else {
				
				$scope.message = null;
				
				$scope.promise = petSrv.findPetById($scope.search.id,
						function success(data) {
							console.log('pet found ' + data.data.petId + ' '
									+ data.data.name);

							console.log(JSON.stringify(data));

							$scope.pets = data.data ? [ data.data ] : [];
							
							if ($scope.pets && $scope.pets.length == 0) {
								$scope.message = 'Pet not found in the store';
							}

						}, function error(data) {
							console.log('pet searching error');
							
							$scope.message = 'An error has occured : ' + data.data.error;
						});
				}
			};

			$scope.getAll = function() {				
				
				$scope.message = null;
				
				$scope.promise = petSrv.getAll(function success(data) {
					console.log('pets found ' + data.data);

					console.log(JSON.stringify(data));

					$scope.pets = data.data;
					
					if ($scope.pets && $scope.pets.length == 0) {
						$scope.message = 'There is not pet in the store';
					}

				}, function error(petId) {
					console.log('pet getAll error');
					
					$scope.message = 'An error has occured : ' + data.data.error;
				});
			}
			
			$scope.getAll();

		});
